var hide = true;

function detail() {
    if (hide === true) {
        showDetail();
    } else {
        hideDetail();
    }
}

function showDetail() {
    document.getElementById("detail").style.display = "inline";
    document.getElementById("btnDetail").value = "Ocultar detalle";
    hide = true;
}

function hideDetail() {
    document.getElementById("detail").style.display = "none";
    document.getElementById("btnDetail").value = "Ver detalle";
    hide = false;
}

function selectAllCheckBoxes(checkBoxController) {
    var shape = document.getElementById("form_one");
    var persons = shape.persons;

    if (persons.length > 0) {
        for (let i = 0; i < persons.length; i++) {
            persons[i].checked = checkBoxController.checked;
        }
    } else {
        if (persons != null) {
            persons.checked = checkBoxController.checked;
        }
    }
}

function validateFormPersonList(button) {
    var shape = document.getElementById("form_one");
    var action = document.getElementById("action");

    if (button.value === "Agregar") {
        action.value = "addPerson";
    }

    if (button.value === "Editar") {
        if (validateSingleCheck()) {
            action.value = "editPerson";
        } else {
            alert("Debe seleccionar solo un elemento para editar");
            return false;
        }
    }

    if (button.value === "Eliminar") {
        action.value = "deletePerson";
    }

    shape.submit();
}

function validateSingleCheck() {
    var shape = document.getElementById("form_one");
    var persons = shape.persons;
    var checkBoxCounter = 0;

    if (persons.length > 0) {
        for (let i = 0; i < persons.length; i++) {
            if (persons[i].checked) {
                checkBoxCounter++;
            }
        }
    } else {
        if (persons.checked) {
            checkBoxCounter++;
        }
    }
    return checkBoxCounter === 1;
}

function cancel() {
    var shape = document.getElementById("form_one");

    window.location = shape.context.value = "/controller";
    shape.action.value = "showPersons";

    shape.submit();
}

function editRegister(id) {
    var shape = document.getElementById("form_one");
    var action = document.getElementById("action");

    if (validateSingleCheck()) {
        alert("Debe seleccionar solo un elemento para editar");
        return false;
    }

    action.value = "editPerson";
    shape.persons[id - 1].checked = true;

    shape.submit();
}