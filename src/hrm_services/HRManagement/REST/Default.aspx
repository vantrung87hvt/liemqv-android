<%@ Page Title="" Language="C#" MasterPageFile="~/Application.master" AutoEventWireup="true" CodeFile="Default.aspx.cs" Inherits="REST_Default" %>

<asp:Content ID="Content3" ContentPlaceHolderID="PageTitle" runat="Server">Home Page</asp:Content>
<asp:Content ID="Content4" ContentPlaceHolderID="MainContent" runat="Server">
   <script type="text/javascript">
       Sys.Application.add_init(function() {
           $create(HRManagement.PersonControl,
           { isREST: true },
           { click: DeletePersonEventHandler }, null, $get("PersonList"));

           GetData();

           function GetData() {

               var url = "../RESTHRService.svc/persons"
               var proxy = new Sys.Net.WebServiceProxy();
               proxy.restInvoke(url,
                     "GET",
                     null,
                     "GetData",
                     PersonsReturnedEventHandler,
                     PersonsFailedEventHandler,
                     null);
           }

           function PersonsReturnedEventHandler(value) {
               // Get ref to the Catalog Control
               var comp = $find('PersonList');

               // Set the data and bind
               comp.set_Persons(value);
               comp.dataBind();
           }

           function PersonsFailedEventHandler(error) {
               // Do the right thing
               alert(error.get_message());
           }

           function DeletePersonEventHandler(sender, args) {
               if (confirm("Do you want to delete person: " + args.FirstName + " " + args.LastName)) {
                   var url = "../RESTHRService.svc/person/" + args.PersonId;
                   var proxy = new Sys.Net.WebServiceProxy();
                   proxy.restInvoke(url,
                     "DELETE",
                     null,
                     "DeletePersonEventHandler", OnDeleted, null, null);
               }
           }

           function OnDeleted() {
               GetData();
           }
       });
    </script>
    <div id="PersonList" style="width:100%" ></div>
</asp:Content>

