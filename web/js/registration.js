var RoomDropDown = $('#select-pos');

$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetAllPosition",
        dataType: 'json',
        success: function (data) {
            console.log(data);
            var s = "<option disabled selected value> -- select position -- </option>";
            RoomDropDown.append(s);
            data.forEach(addRoom);
        },
        error: function (response) {
            console.log(response);
        }
    });
});

function addRoom(data) {
    var s = "<option value = " + data.posID + ">" + data.position + "</option>";
    console.log(s);
    RoomDropDown.append(s);
}