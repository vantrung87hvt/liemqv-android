﻿<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="TestForm1.aspx.cs" Inherits="JSONWebApp.TestForm1" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>WCF demo</title>
    <script type ="text/javascript">
        function CallSingle() {
            http://localhost:50204/JSONDemoService.IJSONService.GetSingleObject(
            document.getElementById("tbId").value,
            document.getElementById("tbName").value,
            CallBackSingle);
        }
        function CallBackSingle(WebServiceResult) {
                    resultDiv = document.getElementById("result1");
                    resultDiv.innerHTML = "You entered: id = " +
                     WebServiceResult.Id + " name = " +
                     WebServiceResult.Name;
                }
    </script>
</head>
<body>
    <form id="form1" runat="server">
        <asp:scriptmanager ID="Scriptmanager1" runat="server">
            <services>
            <asp:ServiceReference Path="AjaxService.svc" />
            </services>
        </asp:scriptmanager>


        <div>
        ID<asp:TextBox ID="tbId" runat="server">21</asp:TextBox>
        Name<asp:TextBox ID="tbName" runat="server">John Doe</asp:TextBox><br />
        <asp:Button ID="btnSelect" runat="server" Text="SingleSelect"
           UseSubmitBehavior="False"  OnClientClick="CallSingle(); return false;"/>
        </div>
        <div id="result1"></div>
    </form>
</body>
</html>
