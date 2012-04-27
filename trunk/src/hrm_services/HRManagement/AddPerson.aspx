<%@ Page Title="" Language="C#" MasterPageFile="~/Application.master" AutoEventWireup="true" CodeFile="AddPerson.aspx.cs" Inherits="AddPerson" %>

<asp:Content ID="Content1" ContentPlaceHolderID="PageTitle" Runat="Server">Add New Person</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="MainContent" Runat="Server">
  <script type="text/javascript">
      Sys.Application.add_init(function() {
          $create(HRManagement.EditPersonControl, null,
           { updatePerson: updatePersonEventHandler }, null, $get("PersonList"));

          function updatePersonEventHandler(sender, args) {
              var hrs = new hr.HRService();
              hrs.AddPerson(args, updatePersonSucceeded);
          }

          function updatePersonSucceeded() {
              alert("Added");
              document.location = "default.aspx";
          }
      });
    </script>
    <div id="PersonList" style="width:100%" ></div>
</asp:Content>

