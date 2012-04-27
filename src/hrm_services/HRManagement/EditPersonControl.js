/// <reference name="MicrosoftAjax.js"/>

Type.registerNamespace("HRManagement");

HRManagement.EditPersonControl = function(element) {
    HRManagement.EditPersonControl.initializeBase(this, [element]);
    this._person = new Object;
    this._person.PersonId = 0;
    this._person.FirstName = "";
    this._person.LastName = "";
    this._person.Department = "";
    this._person.EmailAddress = "";
    this._clickDelegate = null;
    this._isREST = false;
}

HRManagement.EditPersonControl.prototype = {
    initialize: function() {
        HRManagement.EditPersonControl.callBaseMethod(this, 'initialize');
        // Add custom initialization here
        if (this._clickDelegate === null) {
            this._clickDelegate = Function.createDelegate(this, this._updatePersonHandler);
        }
        this.render();
    },
    dispose: function() {
        $clearHandlers(this.get_element());
        //Add custom dispose actions here
        HRManagement.EditPersonControl.callBaseMethod(this, 'dispose');
    },

    // Bind and unbind to click event.
    add_updatePerson: function(handler) {
        this.get_events().addHandler('updatePerson', handler);
    },
    remove_updatePerson: function(handler) {
        this.get_events().removeHandler('updatePerson', handler);
    },

    _updatePersonHandler: function(event, args) {
        var h = this.get_events().getHandler('updatePerson');
        if (h) h(this, args);
    },

    get_isREST: function() { return this._isREST; },
    set_isREST: function(value) {
        this._isREST = value;
    },
    get_Person: function() { return this._person; },
    set_Person: function(value) {
        this._person = value;
    },

    dataBind: function() {
        this.render();
    },
    createInputs: function(tbody, label, value, method) {
        var row = document.createElement("tr");
        tbody.appendChild(row);
        var cellLab = document.createElement("td");
        cellLab.setAttribute("valign", "top");
        cellLab.innerHTML = label;
        row.appendChild(cellLab);
        // Add the textbox
        var cellinp = document.createElement("td");
        cellinp.setAttribute("valign", "top");
        row.appendChild(cellinp);
        var inp = document.createElement('input');
        inp.type = "text";
        inp.size = "30";
        inp.value = value;
        cellinp.appendChild(inp);
        $addHandler(inp, 'change', Function.createCallback(Function.createDelegate(this, method), null));
    },

    render: function() {
        // Get a reference to the passed in element
        var element = this.get_element();

        $clearHandlers(this.get_element());

        // Clear content from the element
        element.innerHTML = '';
        // Add the row
        var subform = document.createElement("table");
        element.appendChild(subform);
        var tbody = document.createElement("tbody");
        subform.appendChild(tbody);

        try {
            // First name
            this.createInputs(tbody, "First Name", this._person.FirstName, this.itemFirstNameUpdated);
            // Last name
            this.createInputs(tbody, "Last Name", this._person.LastName, this.itemLastNameUpdated);
            // Department
            this.createInputs(tbody, "Department", this._person.Department, this.itemDepartmentUpdated);
            // Email
            this.createInputs(tbody, "Email", this._person.EmailAddress, this.itemEmailAddressUpdated);
        }
        catch (ex) {
            alert(ex);
        }

        // LastName
        var row = document.createElement("tr");
        tbody.appendChild(row);
        cellLab = document.createElement("td");
        cellLab.setAttribute("valign", "top");
        cellLab.setAttribute("colspan", "2");
        row.appendChild(cellLab);
        var b = document.createElement("input");
        b.type = "button";
        b.value = "Update";
        cellLab.appendChild(b);
        $addHandler(b, 'click', Function.createCallback(this._clickDelegate, this._person));
    },
    itemFirstNameUpdated: function(sender, args) {
        this._person.FirstName = sender.target.value;
        this.render();
    },
    itemLastNameUpdated: function(sender, args) {
        this._person.LastName = sender.target.value;
        this.render();
    },
    itemDepartmentUpdated: function(sender, args) {
        this._person.Department = sender.target.value;
        this.render();
    },
    itemEmailAddressUpdated: function(sender, args) {
        this._person.EmailAddress = sender.target.value;
        this.render();
    }
}
HRManagement.EditPersonControl.registerClass('HRManagement.EditPersonControl', Sys.UI.Control);

if (typeof(Sys) !== 'undefined') Sys.Application.notifyScriptLoaded();
