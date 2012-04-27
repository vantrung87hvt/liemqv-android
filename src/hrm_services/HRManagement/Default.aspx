<%@ Page Title="" Language="C#" MasterPageFile="~/Application.master" AutoEventWireup="true"
    CodeFile="Default.aspx.cs" Inherits="_Default" %>

<asp:Content ID="Content3" ContentPlaceHolderID="PageTitle" runat="Server">
    Home Page
</asp:Content>
<asp:Content ID="Content4" ContentPlaceHolderID="MainContent" runat="Server">
   <script type="text/javascript">
       Sys.Application.add_init(function() {
           $create(HRManagement.PersonControl, null,
           { click: DeletePersonEventHandler }, null, $get("PersonList"));
           GetData();
           // This function calls the GetProductGrouping AJAX method, using the script proxy
           function GetData() {
               var proxy = new hr.HRService();
               proxy.GetAll(PersonsReturnedEventHandler, PersonsFailedEventHandler, null);
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
                   var hrs = new hr.HRService();
                   hrs.DeletePerson(args.PersonId, null, null, null);
                   GetData();
               }
           }
       });
    </script>
    <div id="PersonList" style="width:100%" ></div>
</asp:Content>
