/// <reference name="MicrosoftAjax.js"/>

Type.registerNamespace("HRManagement");

HRManagement.PersonControl = function(element) {
    HRManagement.PersonControl.initializeBase(this, [element]);
    this.personList = null;
    this._isREST = false;
    this._clickDelegate = null;
}

HRManagement.PersonControl.prototype = {
    initialize: function() {
        HRManagement.PersonControl.callBaseMethod(this, 'initialize');
        // Add custom initialization here
        if (this._clickDelegate === null) {
            this._clickDelegate = Function.createDelegate(this, this._clickHandler);
        }
    },
    dispose: function() {
        $clearHandlers(this.get_element());
        //Add custom dispose actions here
        HRManagement.PersonControl.callBaseMethod(this, 'dispose');
    },
    // Bind and unbind to click event.
    add_click: function(handler) {
        this.get_events().addHandler('click', handler);
    },
    remove_click: function(handler) {
        this.get_events().removeHandler('click', handler);
    },

    _clickHandler: function(event, args) {
        var h = this.get_events().getHandler('click');
        if (h) h(this, args);
    },
    get_isREST: function() { return this._isREST; },
    set_isREST: function(value) {
        this._isREST = value;
    },
    get_Persons: function() { return this.personList; },
    set_Persons: function(value) {
        this.personList = value;
    },

    dataBind: function() {
        this.createChildControls();
    },
    createChildControls: function() {
        // Get a reference to the passed in element
        var element = this.get_element();

        $clearHandlers(this.get_element());

        // Clear content from the element
        element.innerHTML = '';

        // Iterate through the items in the collection
        for (var i = 0; i < this.personList.length; i++) {
            // Add the row
            var person = document.createElement('div');
            Sys.UI.DomElement.addCssClass(person, 'post');
            element.appendChild(person);

            var pnamew = document.createElement("div");
            Sys.UI.DomElement.addCssClass(pnamew, "header");
            person.appendChild(pnamew);
            var pname = document.createElement("h3");
            pname.innerHTML = this.personList[i].FirstName + " " + this.personList[i].LastName;
            pnamew.appendChild(pname);

            var bodyw = document.createElement("div");
            Sys.UI.DomElement.addCssClass(bodyw, "content");
            person.appendChild(bodyw);

            var dept = document.createElement("p");
            dept.innerHTML = "Department: " + this.personList[i].Department;
            bodyw.appendChild(dept);
            dept = document.createElement("p");
            dept.innerHTML = "Email: " + this.personList[i].EmailAddress;
            bodyw.appendChild(dept);
            
            var footer = document.createElement("div");
            Sys.UI.DomElement.addCssClass(footer, "footer");
            person.appendChild(footer);
            var ul = document.createElement("ul");
            footer.appendChild(ul);
            var delP = document.createElement("li");
            Sys.UI.DomElement.addCssClass(delP, "readmore");
            delP.innerHTML = "<a href='#'>Delete</a>";
            ul.appendChild(delP);
            $addHandler(delP, 'click', Function.createCallback(this._clickDelegate, this.personList[i]));
            var editP = document.createElement("li");
            Sys.UI.DomElement.addCssClass(editP, "readmore");
            editP.innerHTML = "<a href='EditPerson.aspx?id=" + this.personList[i].PersonId + "'>Edit</a>";
            ul.appendChild(editP);
        }
    }
}
HRManagement.PersonControl.registerClass('HRManagement.PersonControl', Sys.UI.Control);

if (typeof(Sys) !== 'undefined') Sys.Application.notifyScriptLoaded();
