var dropdown = $("#select-PO");
var programCode = sessionStorage.getItem("programCode");

$(document).ready(function () {
    getAllPO(programCode);
    dropdown.change(function () {
        var codePO = dropdown.val();
        $.ajax({
            type: "GET",
            url: "/OBESystem/GetSpecificPO?SelectedPO=" + codePO,
            dataType: 'json',
            success: function (data) {
                console.log(data);
                $("#PO-description").text(data.codePO + ": " + data.description);
                $('#hidden-codePO').val(data.codePO);
            },
            error: function (response) {
                console.log(response);
            }
        });

    });
});

function getAllPO(program) {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetAllPO?SelectedProgram=" + program,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            var s = "<option disabled selected value> -- select an option -- </option>";
            dropdown.append(s);
            data.forEach(addPO);
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function addPO(data) {
    console.log("Entered addPO: " + data.codePO);
    var s = "<option value = " + data.codePO + ">" + data.codePO + "</option>";
    dropdown.append(s);
}
