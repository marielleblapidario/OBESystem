var facultyDropDown = $('#select-faculty');

$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetAllFaculty",
        dataType: 'json',
        success: function (data) {
            console.log(data);
            var s = "<option disabled selected value> -- select an option -- </option>";
            facultyDropDown.append(s);
            data.forEach(addFaculty);
        },
        error: function (response) {
            console.log(response);
        }
    });
});

function addFaculty(data) {
    var s = "<option value = " + data.userID + ">" + data.fullName + "</option>";
    facultyDropDown.append(s);
}