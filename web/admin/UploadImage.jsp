<html>
    <head>
        <title>Upload Files</title>
        <jsp:include page="stylesheet.jsp"/>
    </head>
    <body>
        <jsp:include page="mp_header.jsp"/>
        <form action="#" method="post">
            <table>
                <tr>
                    <td>
                        <fieldset><legend><strong><em>Upload Image</em></strong> </legend>
                            <table>
                                <tr>
                                    <td align="left">
                                        <label id="lblMsg" style="color: red">lblMsg</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Image To Be Uploaded</td>
                                    <td><input type="file" name="flImageToBeUploaded" id="flImageToBeUploaded" ></td>
                                </tr>
                                
                                </tr>
                                <tr>
                                    <td colspan="2" align="right"><input type="submit" name="sbtnUpload" value="Upload"> <input type="button" name="sbtnCancel" value="Cancel"></td>
                                </tr>	
                            </table>
                        </fieldset>
                    </td>
                </tr>
            </table>
        </form>
        
    </body>
</html>