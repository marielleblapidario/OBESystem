var programDropDown = $("#select-course");

$(document).ready(function () {
    getAllCourse();
    $('#confirm-btn').click(function () {
        var title = $('#select-course option:selected').text();
        var codeCourse = $('#select-course option:selected').val();

        sessionStorage.setItem("title", title);
        var s = sessionStorage.getItem("title");
        sessionStorage.setItem("codeCourse", codeCourse);
        var ss = sessionStorage.getItem("codeCourse");

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
            data.forEach(addCourse);
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function addCourse(data) {
    console.log("Entered addCourse: " + data.codeCourse);
    var s = "<option value = " + data.codeCourse + ">" + data.title + "</option>";
    programDropDown.append(s);
}
