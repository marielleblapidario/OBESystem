var list = [];
var courseList = [];
var preRequisite = [];
var term = [];
var yearLevel = [];
var table = $("#data");
var dropDownCourse = $("#select-course");
var programDropDown = $("#select-program");
var yearPicker = $('#startYear');
var rowCount = 0;
var arrRowCount = [];

$(document).ready(function () {
    $("#table").hide();
    getAllTerm();
    getAllYearLevel();
    changeYear();
    programDropDown.change(function () {
        clearTable();
        courseList = [];
        preRequisite = [];
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
                console.log("rowCount: " + rowCount);

                var rowCourse = {count: rowCount, courseID: data.courseID, title: data.title};
                arrRowCount.push(rowCourse);

                var tr = '';
                var openingTr = '<tr id =tr' + rowCount + '>';
                tr += openingTr;

                var code = '<td>' + data.codeCourse
                        + '<input type="hidden" name="codeCourse" class="readonlyWhite" value="' + data.codeCourse + '" />'
                        + '<input type="hidden" name="courseID" class="readonlyWhite" value="' + data.courseID + '" />'
                        + '</td>';
                tr += code;

                var title = '<td>' + data.title
                        + '<input type="hidden" name="title" class="readonlyWhite"  value="' + data.title + '" />'
                        + '</td>';
                tr += title;

                var units = '<td>' + data.units
                        + '<input type="hidden" name="units" class="readonlyWhite" value="' + data.units + '" />'
                        + '</td>';
                tr += units;

                var OYS = '<td><select name="yearLevel" class="form-control">';
                tr += OYS;
                for (var x = 0; x < yearLevel.length; x++) {
                    var s = "<option value=" + yearLevel[x].yearLevel + ">" + yearLevel[x].yearLevel + "</option>";
                    tr += s;
                }
                var CYS = '</select></td>';
                tr += CYS;

                var OTS = '<td><select name="term" class="form-control">';
                tr += OTS;
                for (var x = 0; x < term.length; x++) {
                    var s = "<option value=" + term[x].term + ">" + term[x].term + "</option>";
                    tr += s;
                }
                var CTS = '</select></td>';
                tr += CTS;
                
                console.log("preRequisite size: " + preRequisite.length);
                
                var OPS = '<td><select name="prerequisite" class="form-control">';
                tr += OPS;
                var a = "<option selected value=\"-1\"> -- select an option -- </option>";
                tr += a;
                for (var x = 0; x < preRequisite.length; x++) {
                    var s = "<option value=" + preRequisite[x].courseID + ">" + preRequisite[x].title + "</option>";
                    tr += s;
                }
                var CPS = '</select></td>';
                tr += CPS;

                var tool = '<td><button type="button" id="delete' + rowCount + '" class="btn btn-danger btn-xs"><i class="fa fa-trash" onClick="deleteRow(' + rowCount + ')"></i></button></td>';
                tr += tool;

                var closingTr = '</tr>';
                tr += closingTr;

                table.append(tr);
                rowCount++;
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
                preRequisite.push(course);

            }
            showCourses();
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function getAllTerm() {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetAllTrimester",
        dataType: 'json',
        success: function (data) {
            console.log(data);
            for (var x = 0; x < data.length; x++) {
                term.push(data[x]);
            }
            showCourses();
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function getAllYearLevel() {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetAllYearLevel",
        dataType: 'json',
        success: function (data) {
            console.log(data);
            for (var x = 0; x < data.length; x++) {
                yearLevel.push(data[x]);
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

function clearTable() {
    console.log("arrRowCount size: " + arrRowCount.length);
    for (var x = 0; x < arrRowCount.length; x++) {
        console.log("arrRowCount count: " + arrRowCount[x].count);
        var tr = 'tr' + arrRowCount[x].count;
        document.getElementById(tr).remove();
        arrRowCount.splice(x, 1);
    }
    $("#table").hide();
    console.log("arrRowCount length: " + arrRowCount.length);
    rowCount = 0;
}

function deleteRow(num) {
    var retVal = confirm("Are you sure you want to delete this row?");
    if (retVal === true) {
        var tr = 'tr' + num;
        document.getElementById(tr).remove();

        console.log("arrRowCount size: ", arrRowCount.length);
        var pos = 0;
        var add = 0;
        for (var y = 0; y < arrRowCount.length; y++) {
            console.log("y: " + y);
            if (arrRowCount[y].count == num) {
                console.log("count: " + arrRowCount[y].count);
                pos = y;
            }
        }
        for (var x = 0; x < preRequisite.length; x++) {
            if (arrRowCount[pos].courseID == preRequisite[x].courseID) {
                console.log("pushed: " + preRequisite[x].title);
                add = x;
            }
        }
        courseList.push(preRequisite[add]);
        arrRowCount.splice(pos, 1);
        console.log("arrRowCount newSize: ", arrRowCount.length);
        showCourses();
        if(arrRowCount.length == 0){
            $("#table").hide();
        }
        return true;
    } else {
        console.log("cancelled");
        return false;
    }
}

function changeYear() {
    yearPicker.on("input change", function (e) {
        console.log("changed: ", e.target.value);
        var sYear = +e.target.value;
        var eYear = sYear + 1;
        $("#endYear").val(eYear);
    });
}