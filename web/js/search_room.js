var RoomDropDown = $('#select-room');

$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetAllRoom",
        dataType: 'json',
        success: function (data) {
            console.log(data);
            var s = "<option disabled selected value> -- select an option -- </option>";
            RoomDropDown.append(s);
            data.forEach(addRoom);
        },
        error: function (response) {
            console.log(response);
        }
    });
});

function addRoom(data) {
    var s = "<option value = " + data.roomID + ">" + data.roomTitle + "</option>";
    RoomDropDown.append(s);
}