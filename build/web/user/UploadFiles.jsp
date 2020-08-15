<html>
    <head>
        <title>Upload Files</title>
        <jsp:include page="stylesheet.jsp"/>
    </head>
    <body>
        <jsp:include page="mp_header.jsp"/>
        <form action="../UploadFile" method="post" enctype="multipart/form-data">


            <table>
                <tr>
                    <td>
                        <fieldset><legend><strong><em>Upload Files</em></strong> </legend>
                            <table>
                                <tr>
                                    <td align="left">
                                        <label id="lblMsg" style="color: red"></label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Parent Folder</td>
                                    <td>${sessionScope.folderName}</td>			
                                </tr>	
                                <tr>
                                    <td>File To Be Uploaded</td>
                                    <td><input type="file"  name="flFileToBeUploaded" id="flFileToBeUploaded" ></td>
                                </tr>	
                                <tr>
                                    <td valign="top">Description</td>
                                    <td><textarea name="taDescription" id="taDescription" rows="5" cols="30"></textarea></td>
                                </tr>	
                                <tr>
                                    <td colspan="2" align="right"><input type="submit" name="sbtnUpload" value="Upload" class="button"> <input type="button" name="sbtnCancel" value="Cancel" class="button" onclick="history.go(-1)"></td>			
                                </tr>	

                            </table>
                        </fieldset>
                    </td>
                </tr>
            </table>
           
        </form>
    </body>
</html>
