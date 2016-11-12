var programDropDown = $("#select-course");

$(document).ready(function () {
    getAllCourse();
    $('#confirm-btn').click(function () {
        var title = $('#select-course option:selected').text();
        var courseID = $('#select-course option:selected').val();

        sessionStorage.setItem("title", title);
        var s = sessionStorage.getItem("title");
        sessionStorage.setItem("courseID", courseID);
        var ss = sessionStorage.getItem("courseID");

        console.log('code: ' + ss + ' title: ' + s);
    });
}

);

function getAllCourse() {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetAllCourse",
        dataType: 'json',
        success: function (data) {
            console.log(data);
            var s = "<option disabled selected value> -- select an option -- </option>";
            programDropDown.append(s);
            data.forEach(addCourse);
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function addCourse(data) {
    console.log("Entered addCourse: " + data.codeCourse);
    var s = "<option value = " + data.courseID + ">" + data.codeCourse + "</option>";
    programDropDown.append(s);
}
