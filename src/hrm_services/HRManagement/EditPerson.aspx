<%@ Page Title="" Language="C#" MasterPageFile="~/Application.master" AutoEventWireup="true" CodeFile="EditPerson.aspx.cs" Inherits="EditPerson" %>

<asp:Content ID="Content1" ContentPlaceHolderID="PageTitle" Runat="Server">Edit Person</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="MainContent" Runat="Server">
  <script type="text/javascript">
      function getArgs() {
          var args = new Object();
          var query = location.search.substring(1);
          var pairs = query.split("&");
          for (var i = 0; i < pairs.length; i++) {
              var pos = pairs[i].indexOf('=');
              if (pos == -1) continue;
              var argname = pairs[i].substring(0, pos);
              var value = pairs[i].substring(pos + 1);
              args[argname] = unescape(value);
          }
          return args;
      }

      Sys.Application.add_init(function() {
          $create(HRManagement.EditPersonControl, null,
           { updatePerson: updatePersonEventHandler }, null, $get("PersonList"));
          GetData();
          // This function calls the GetProductGrouping AJAX method, using the script proxy
          function GetData() {
              var proxy = new hr.HRService();
              var args = getArgs();
              if (args.id) {
                  var id = parseInt(args.id);
                  proxy.GetPerson(id, PersonsReturnedEventHandler, PersonsFailedEventHandler, null);
              }
              else {
                  alert("Invalid request");
              }
          }

          function PersonsReturnedEventHandler(value) {
              // Get ref to the Catalog Control
              var comp = $find('PersonList');

              // Set the data and bind
              comp.set_Person(value);
              comp.dataBind();
          }

          function PersonsFailedEventHandler(error) {
              // Do the right thing
              alert(error.get_message());
          }

          function updatePersonEventHandler(sender, args) {
              var hrs = new hr.HRService();
              hrs.UpdatePerson(args.PersonId, args, updatePersonSucceeded);

              GetData();
          }

          function updatePersonSucceeded() {
              alert("Updated");
          }
      });
    </script>
    <div id="PersonList" style="width:100%" ></div>
</asp:Content>

