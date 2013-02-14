package com.itude.mobile.android.util;

import java.io.UnsupportedEncodingException;

public class EncryptionUtil
{
  private final byte[] s;
  private int          the_i;
  private int          the_j;
  private int          next_j = -666; // not really needed after all

  public EncryptionUtil()
  {
    s = new byte[256];
    the_i = the_j = 0;
  }

  public EncryptionUtil(byte[] s)
  {
    this.s = s;
    the_i = the_j = 0;
  }

  /**
   * run the key scheduler for n rounds, using key[0]...key[n-1]
   */
  public void ksa(int n, byte[] key, boolean printstats)
  {
    int keylength = key.length; // NOT keylength above!!
    int i = 0;
    for (i = 0; i < 256; i++)
      s[i] = (byte) i;
    the_j = 0;
    for (the_i = 0; the_i < n; the_i++)
    {
      the_j = (the_j + posify(s[the_i]) + posify(key[the_i % keylength])) % 256;
      sswap(s, the_i, the_j);
    }
    if (n != 256)
    {
      next_j = (the_j + posify(s[n]) + posify(key[n % keylength])) % 256;
    }
    if (printstats)
    {
      System.out.print("s_" + (n - 1) + ":");
      for (int k = 0; k <= n; k++)
        System.out.print(" " + posify(s[k]));
      System.out.print("   j_" + (n - 1) + "=" + the_j);
      System.out.print("   j_" + n + "=" + next_j);
      System.out.print("   s_" + (n - 1) + "[j_" + (n - 1) + "]=" + posify(s[the_j]));
      System.out.print("   s_" + (n - 1) + "[j_" + (n) + "]=" + posify(s[next_j]));
      if (s[1] != 0) System.out.print("   s[1]!=0");
      System.out.println();
    }
  }

  public void ksa(byte[] key)
  {
    ksa(256, key, false);
  }

  public void init()
  {
    the_i = the_j = 0;
  }

  byte nextVal()
  {
    the_i = (the_i + 1) % 256;
    the_j = (the_j + posify(s[the_i])) % 256;
    sswap(s, the_i, the_j);
    byte value = s[(posify(s[the_i]) + posify(s[the_j])) % 256];
    return value;
  }

  // returns i for which x = s[i]
  byte inverse(byte x)
  {
    int i = 0;
    while (i < 256)
    {
      if (x == s[i]) return (byte) i;
      i++;
    }
    return (byte) 0; // never get here
  }

  int the_i()
  {
    return this.the_i;
  }

  int the_j()
  {
    return this.the_j;
  }

  int next_j()
  {
    return this.next_j;
  }

  int s(int n)
  {
    return posify(s[(byte) n]);
  }

  private static void sswap(byte[] s, int i, int j)
  {
    byte temp = s[i];
    s[i] = s[j];
    s[j] = temp;
  }

  // returns value of b as an unsigned int
  public static int posify(byte b)
  {
    if (b >= 0) return b;
    else return 256 + b;
  }

  static public String byte2string(byte b)
  {
    int high = (b >> 4) & 0x0F;
    int low = (b & 0x0F);
    String convert = "0123456789abcdef";
    // convert = "0123456789ABCDEF";	// uncomment if you want uppercase
    String result = "";
    result += convert.charAt(high);
    result += convert.charAt(low);
    return result;
  }

  // given a char '0' ...'f' or 'F', returns 0..15
  static private int hexval(char c)
  {
    if ('0' <= c && c <= '9') return (c - '0');
    if ('a' <= c && c <= 'f') return (c - 'a' + 10);
    if ('A' <= c && c <= 'F') return (c - 'A' + 10);
    return 0;
  }

  static public byte[] string2byte(String s)
  {
    int length = s.length();
    length = (length / 2);
    byte[] buf = new byte[length];
    for (int i = 0; i < length; i++)
    {
      int nyb1 = hexval(s.charAt(2 * i));
      int nyb2 = hexval(s.charAt(2 * i + 1));
      buf[i] = (byte) ((nyb1 * 16) + nyb2);
    }
    return buf;
  }

  /**
   * encrypt is for testing; key can be any length
   */
  static public byte[] encrypt(String key, String message)
  {
    return encrypt(key.getBytes(), message.getBytes());
  }

  /**
   * encrypt is for testing; key can be any length
   */
  static public byte[] encrypt(byte[] key, byte[] message)
  {
    byte[] outbuf = new byte[message.length];
    EncryptionUtil r = new EncryptionUtil();
    r.ksa(key);
    r.init();
    for (int i = 0; i < message.length; i++)
    {
      outbuf[i] = (byte) (message[i] ^ r.nextVal());
    }
    return outbuf;
  }

  static public byte[] encodeBytes(byte[] bytes, String encodingType)
  {
    String encodedString = "";
    try
    {
      encodedString = new String(bytes, "Windows-1252");
    }
    catch (UnsupportedEncodingException e)
    {
    }
    return encodedString.getBytes();

  }

  static public String byte2string(byte[] b)
  {
    String result = "";
    for (int i = 0; i < b.length; i++)
    {
      result += byte2string(b[i]);
    }
    return result;
  }

  static private int keylength = 8; // keylength for WEP; deprecated

  /**
   * buildkey is for WEP keys only
   */
  public static byte[] buildkey(byte[] IV, byte[] shortkey)
  {
    byte[] key = new byte[keylength];
    int ivlen = IV.length;
    int i = 0;
    for (i = 0; i < ivlen; i++)
      key[i] = IV[i];
    for (i = ivlen; i < keylength; i++)
      key[i] = shortkey[i - ivlen];
    return key;
  }

}
