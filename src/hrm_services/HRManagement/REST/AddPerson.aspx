<%@ Page Title="" Language="C#" MasterPageFile="~/Application.master" AutoEventWireup="true" CodeFile="AddPerson.aspx.cs" Inherits="REST_AddPerson" %>

<asp:Content ID="Content1" ContentPlaceHolderID="PageTitle" Runat="Server">Add New Person</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="MainContent" Runat="Server">
  <script type="text/javascript">
      Sys.Application.add_init(function() {
          $create(HRManagement.EditPersonControl, null,
           { updatePerson: updatePersonEventHandler }, null, $get("PersonList"));

          function updatePersonEventHandler(sender, args) {
              var url = "../RESTHRService.svc/person/0";
              var proxy = new Sys.Net.WebServiceProxy();
              proxy.restInvoke(url,
                     "PUT",
                     args,
                     "updatePersonEventHandler",
                     updatePersonSucceeded,
                     null,
                     null);
          }

          function updatePersonSucceeded() {
              alert("Added");
              document.location = "default.aspx";
          }
      });
    </script>
    <div id="PersonList" style="width:100%" ></div>
</asp:Content>

