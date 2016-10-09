var list = [];
var courseList = [];
var table = $("#example1").DataTable();
var dropDownCourse = $("#select-course");
var programDropDown = $("#select-program");

$(document).ready(function () {
    $("#table").hide();
    programDropDown.change(function () {
        table.clear().draw();
        courseList = [];
        var program = programDropDown.val();
        console.log("program selected: " + program);
        getAllCourse(program);
    });
});

$("#button-add").click(function () {
    var selectedCourse = $("#select-course option:selected").val();
    list.push(selectedCourse);

    for (var i = 0; i < courseList.length; i++) {
        console.log("for loop: " + courseList[i].courseID + " compare to " + selectedCourse);
        if (courseList[i].courseID == selectedCourse) {
            console.log("entered boolean of compare");
            courseList.splice(i, 1);
            break;
        }
    }
    showCourses();
    if (list.length > 0) {
        $.ajax({
            type: "GET",
            url: "/OBESystem/GetCourseByCode?SelectedCourse=" + selectedCourse,
            dataType: 'json',
            success: function (data) {
                console.log(data);
                var code = data.codeCourse
                        + '<input type="hidden" name="codeCourse" class="readonlyWhite" value="' + data.coudeCourse + '" />'
                        + '<input type="hidden" name="courseID" class="readonlyWhite" value="' + data.courseID + '" />';
                var title = data.title
                        + '<input type="hidden" name="title" class="readonlyWhite"  value="' + data.title + '" />';
                var units = data.units
                        + '<input type="hidden" name="units" class="readonlyWhite" value="' + data.units + '" />';
                var tool = "<button type=\"button\" id=\"deleteRow\" class=\"btn btn-danger btn-xs\"><i class=\"fa fa-trash\"></i></button>";

                table.row.add([code, title, units, tool]);
                table.draw();
            },
            error: function (response) {
                console.log(response);
            }
        });

        $("#table").show();
    } else {
        $("#table").hide();
    }

});

function getAllCourse(program) {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetCoursesOnProgram?SelectedProgram=" + program,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            for (var x = 0; x < data.length; x++) {
                var course = {courseID: data[x].courseID, title: data[x].title};
                courseList.push(course);

            }
            showCourses();
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function showCourses() {
    dropDownCourse.find('option').remove().end();
    var a = "<option disabled selected value> -- select an option -- </option>";
    dropDownCourse.append(a);
    for (var x = 0; x < courseList.length; x++) {
        var s = "<option value=" + courseList[x].courseID + ">" + courseList[x].title + "</option>";
        dropDownCourse.append(s);
    }
}
