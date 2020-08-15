function isEmpty(strEmpty)
{
    if (strEmpty == "")
    {

        return true;
    }
    return false;
}

function hasSpace(strSpace)
{
    if (strSpace.indexOf(" ") != -1)
    {
        return true;
    }
    return false;
}

function isAlphaNumeric(strAlphaNumeric)
{
    for (i = 0; i < strAlphaNumeric.length; i++)
    {
        ch = strAlphaNumeric.charAt(i);
        if (!((ch >= "a" && ch <= "z") || (ch >= "A" && ch <= "Z") || (ch >= "0" && ch <= "9")))
        {
            return false;
        }
    }
    return true;
}

function isNumeric(strNumeric)
{

    for (i = 0; i < strNumeric.length; i++)
    {
        ch = strNumeric.charAt(i);
        if (ch < "0" || ch > "9")
        {
            return false;
        }
    }
    return true;
}
function contSize(sizenum)
{
    var sizen = sizenum.length;
    if (sizen < 10 || sizen > 10)
    {
        return true;
    }
    return false;

}

function isInRange(minLength, maxLength, strRange)
{

    var length = strRange.length;

    if (length < minLength || length > maxLength)
    {
        return false;

    }

    else
    {
        return true;

    }
}
function matchPassword(confirmpass, pass)
{
    if (confirmpass == pass)
    {
        return true;
    }
    else
    {
        return false;
    }
}


function isName(strName)
{
    for (i = 0; i < strName.length; i++)
    {
       
        ascii = strName.charCodeAt(i);
        
       if (!((ascii >= 65 && ascii <= 90) || (ascii >= 97 && ascii <= 122) || (ascii==32)))
        {
            return false;
        }
    }
    return true;
}


function isFutureDate(strDate)
{
    var systemDate = new Date();
    var selectedDate = new Date(strDate);

    if (systemDate < selectedDate)
    {
        return true;
    }
    return false;
}

function isEmail(strEmail)
{

    var lastDot = strEmail.lastIndexOf(".");
    var firstAt = strEmail.indexOf("@");
    var lastAt = strEmail.lastIndexOf("@");
    var length = strEmail.length;

    if (lastDot == -1 || firstAt == -1) {
        return false;
    }

    for (i = 0; i < length; i++)
    {
        ch = strEmail.charAt(i);
        if (!((ch >= "a" && ch <= "z") || (ch >= "A" && ch <= "Z") || (ch >= "0" && ch <= "9") || ch == "_" || ch == "." || ch == "@"))
        {
            return false;
        }
    }

    if (firstAt != lastAt)
    {
        return false;
    }

    if ((strEmail.indexOf("..") != -1) || (strEmail.indexOf("._") != -1) || (strEmail.indexOf(".@") != -1) || (strEmail.indexOf("@.") != -1) || (strEmail.indexOf("@_") != -1) || (strEmail.indexOf("__") != -1) || (strEmail.indexOf("_.") != -1) || (strEmail.indexOf("_@") != -1))
    {
        return false;
    }

    if ((length - lastDot < 3) || (length - lastDot > 4) || (lastAt > lastDot))
    {
        return false;
    }
    return true;

}
function noValue(countryname)
{
    if (countryname == "select")
    {
        return true;
    }
    else
        return false;
}
function isDate(strDate)
{
    var length=strDate.length;
    if(length>9)
    {
        return false;
    }
    
    else
    {
        return true;
    }
    
    }