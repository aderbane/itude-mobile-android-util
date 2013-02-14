package com.itude.mobile.android.util.tests;

import junit.framework.TestCase;

public class MBCacheWriterTest extends TestCase
{

  //  public void testPutInCache()
  //  {
  //    HashMap<String, String> registry = new HashMap<String, String>();
  //    String registryFileName = "registry_test.cache";
  //    HashMap<String, String> documentTypes = new HashMap<String, String>();
  //    Hashtable<String, String> ttls = new Hashtable<String, String>();
  //    String ttlsFileName = "ttls_test.cache";
  //    String fileName = "cache.cache";
  //    byte[] data = null;
  //    HashMap<String, byte[]> temporaryMemoryCache = new HashMap<String, byte[]>();
  //    String key = null;
  //
  //    MBCacheWriter writer = new MBCacheWriter(registry, registryFileName, documentTypes, ttls, ttlsFileName, fileName, data,
  //        temporaryMemoryCache, key);
  //    writer.start();
  //  }

  @Override
  protected void tearDown() throws Exception
  {
    //    File root = getContext().getFilesDir();
    //    File registry = new File(root, "registry_test.cache");
    //    if (registry.exists()) registry.delete();
    //    File ttls = new File(root, "ttls_test.cache");
    //    if (ttls.exists()) ttls.delete();
    //    File cache = new File(root, "cache.cache");
    //    if (cache.exists()) cache.delete();

    super.tearDown();
  }

}
