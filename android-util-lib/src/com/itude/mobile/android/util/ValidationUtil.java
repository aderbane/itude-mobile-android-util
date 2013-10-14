/*
 * (C) Copyright ItudeMobile.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.itude.mobile.android.util;

/**
 * 
 *
 */
public final class ValidationUtil
{

  private ValidationUtil()
  {

  }

  public static boolean validateEmail(String value)
  {
    if (StringUtil.isBlank(value))
    {
      return true;
    }

    if (!StringUtil.checkPattern("^[0-9a-zA-Z][\\w+_.-]*@\\w[\\w+_.-]*\\.[a-zA-Z]{2,9}$", value))
    {
      return false;
    }

    return true;
  }

  public static boolean validateName(String name)
  {
    boolean validate = true;
    if (StringUtil.isBlank(name))
    {
      validate = false;
    }
    else if (name.length() < 2)
    {
      validate = false;
    }
    else if (!StringUtil.checkPattern("^(?![0-9])[a-z A-Z]{1,}$", name))
    {
      validate = false;
    }
    return validate;
  }

  public static boolean validateInitials(String initials)
  {
    boolean validate = true;
    if (StringUtil.isBlank(initials))
    {
      validate = false;
    }
    else if (!StringUtil.checkPattern("^(?![0-9])[a-z .A-Z]{1,}$", initials))
    {
      validate = false;
    }
    return validate;
  }

  public static boolean validatePrefix(String prefix)
  {
    boolean validate = true;
    if (StringUtil.isNotBlank(prefix) && !StringUtil.checkPattern("^(?![0-9])[a-z A-Z]{1,}$", prefix))
    {
      validate = false;
    }
    return validate;
  }

  public static boolean validateSurname(String surname)
  {
    boolean validate = true;
    if (StringUtil.isBlank(surname))
    {
      validate = false;
    }
    else if (surname.length() < 2)
    {
      validate = false;
    }
    else if (!StringUtil.checkPattern("^(?![0-9])[a-zA-Z ]{1,}$", surname))
    {
      validate = false;
    }
    return validate;
  }

  public static boolean validateBirthdate(String birthDate)
  {
    boolean validate = true;
    if (StringUtil.isBlank(birthDate))
    {
      validate = false;
    }
    return validate;

  }

  public static boolean validateAddress(String address)
  {
    boolean validate = true;
    if (StringUtil.isBlank(address))
    {
      validate = false;
    }
    else if (address.length() < 2)
    {
      validate = false;
    }
    else if (!StringUtil.checkPattern("^(?![0-9])[a-zA-Z ]{1,}$", address))
    {
      validate = false;
    }
    return validate;
  }

  public static boolean validateNumber(String houseNumber)
  {
    boolean validate = true;
    if (StringUtil.isBlank(houseNumber))
    {
      validate = false;
    }
    else if (!StringUtil.checkPattern("^(?![A-Za-z])[1-9]{1}[0-9]{0,}$", houseNumber))
    {
      validate = false;
    }
    return validate;
  }

  public static boolean validateZipcode(String zipCode)
  {
    boolean validate = true;
    if (StringUtil.isBlank(zipCode))
    {
      validate = false;
    }
    else if (!StringUtil.checkPattern("^[0-9]{4}[a-z|A-Z]{2}$", zipCode))
    {
      validate = false;
    }
    return validate;
  }

  public static boolean validateCity(String city)
  {
    boolean validate = true;
    if (StringUtil.isBlank(city))
    {
      validate = false;
    }
    else if (city.length() < 2)
    {
      validate = false;
    }
    else if (!StringUtil.checkPattern("^(?![0-9])[a-zA-Z ]{1,}$", city))
    {
      validate = false;
    }
    return validate;
  }

}
