var list = [];
var courseList = [];
var preRequisite = [];
var curriculumList = [];
var term = [];
var yearLevel = [];
var table = $("#data");
var dropDownCourse = $("#select-course");
var programDropDown = $("#select-program");
var curriculumDropDown = $("#select-import");
var spanUnits = $("#total-units");
var yearPicker = $('#startYear');
var rowCount = 0;
var totalUnits = 0;
var arrRowCount = [];
var codeCurriculum;
var program;

$(document).ready(function () {
    $("#table").hide();
    getAllTerm();
    getAllYearLevel();
    changeYear();
    spanUnits.text(totalUnits);
    showCurriculum();
    showCourses();
    programDropDown.change(function () {
        clearTable();
        courseList = [];
        preRequisite = [];
        curriculumList = [];
        list = [];
        totalUnits = 0;
        spanUnits.text(totalUnits);
        program = programDropDown.val();
        console.log("program selected: " + program);
        GetCurriculumUnderProgram(program);
        getAllCourse(program);
    });
    curriculumDropDown.change(function () {
        codeCurriculum = curriculumDropDown.val();
        console.log("codeCurriculum: ", codeCurriculum);
    });  
});
//imports all the courses in an existing curriculum
$("#button-import").click(function () {
    clearTable();
    courseList = [];
    preRequisite = [];
    list = [];
    totalUnits = 0;
    getAllCourse(program);
    spanUnits.text(totalUnits);
    getSpecificMapCurriculumToCourse(codeCurriculum);
    $('#table').show();
});

//adds a course into the list of courses under the curriculum
$("#button-add").click(function () {
    var selectedCourse = $("#select-course option:selected").val();
    //removes the added course in the list of course choices
    for (var i = 0; i < courseList.length; i++) {
        console.log("for loop: " + courseList[i].courseID + " compare to " + selectedCourse);
        if (courseList[i].courseID == selectedCourse) {
            courseList.splice(i, 1);
            break;
        }
    }
    //refreshes the list of course choices
    showCourses();
    //adds the course into the table
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetCourseByCode?SelectedCourse=" + selectedCourse,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            console.log("rowCount: " + rowCount);
            
            //store the details of the specific row
            var rowCourse = {count: rowCount, courseID: data.courseID, title: data.title, units: data.units};
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

            var tool = '<td><button title="delete" type="button" id="delete' + rowCount + '" class="btn btn-danger btn-xs"><i class="fa fa-trash" onClick="deleteRow(' + rowCount + ')"></i></button></td>';
            tr += tool;

            var closingTr = '</tr>';
            tr += closingTr;

            table.append(tr);
            rowCount++;

            totalUnits = totalUnits + parseInt(data.units);
            spanUnits.text(totalUnits);
        },
        error: function (response) {
            console.log(response);
        }
    });
    $("#table").show();
});

function getAllCourse(program) {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetCoursesOnProgram?SelectedProgram=" + program,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            for (var x = 0; x < data.length; x++) {
                var course = {courseID: data[x].courseID, title: data[x].codeCourse};
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
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function GetCurriculumUnderProgram(program) {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetCurriculumUnderProgram?SelectedProgram=" + program,
        dataType: 'json',
        success: function (data) {
            console.log("--->entered get curriculums under program");
            console.log(data);
            for (var x = 0; x < data.length; x++) {
                var course = {curriculumID: data[x].codeCurriculum, title: data[x].title};
                curriculumList.push(course);
            }
            showCurriculum();
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function getSpecificMapCurriculumToCourse(codeCurriculum) {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetSpecificMapCurriculumToCourse?SelectedCurriculum=" + codeCurriculum,
        dataType: 'json',
        success: function (data) {
            console.log("--> entered get specific courses of curriculum");
            console.log(data);
            //displays all the courses from the imported curriculum
            for (var a = 0; a < data.length; a++) {
                test(data[a]);
            }
            spanUnits.text(totalUnits);
            //remove existing courses inside courseList
            console.log("courseList size: ", courseList.length);
            for (var i = courseList.length - 1; i >= 0; i--) {
                for (var j = 0; j < data.length; j++) {
                    if (courseList[i].courseID == data[j].courseID) {
                        courseList.splice(i, 1);
                        break;
                    }
                }
            }
            console.log("courseList new size: ", courseList.length);
            showCourses();
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function showCurriculum() {
    curriculumDropDown.find('option').remove().end();
    var a = "<option disabled selected value> -- select an option -- </option>";
    curriculumDropDown.append(a);
    for (var x = 0; x < curriculumList.length; x++) {
        var s = "<option value=" + curriculumList[x].curriculumID + ">" + curriculumList[x].title + "</option>";
        curriculumDropDown.append(s);
    }
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
    console.log("arrRowCount length before: " + arrRowCount.length);
    for (var x = 0; x < arrRowCount.length; x++) {
        var tr = 'tr' + arrRowCount[x].count;
        document.getElementById(tr).remove();
    }
    arrRowCount = [];

    $("#table").hide();
    console.log("arrRowCount length after: " + arrRowCount.length);
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
        //gets the position of the column in the table
        for (var y = 0; y < arrRowCount.length; y++) {
            console.log("y: " + y);
            if (arrRowCount[y].count == num) {
                console.log("count: " + arrRowCount[y].count);
                pos = y;
            }
        }
        //gets the index of the course to be added back into the courselist
        for (var x = 0; x < preRequisite.length; x++) {
            if (arrRowCount[pos].courseID == preRequisite[x].courseID) {
                console.log("pushed: " + preRequisite[x].title);
                add = x;
            }
        }
        totalUnits = totalUnits - parseInt(arrRowCount[pos].units);
        spanUnits.text(totalUnits);
        courseList.push(preRequisite[add]);
        arrRowCount.splice(pos, 1);
        console.log("arrRowCount newSize: ", arrRowCount.length);
        showCourses();
        if (arrRowCount.length == 0) {
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

function test(data) {
    var courseID = data.courseID;
    var codeCourse = data.codeCourse;
    var courseTitle = data.courseTitle;
    var units = data.units;
    var yearLevelV = data.yearLevel;
    var termV = data.term;
    var preRequisiteV = data.preRequisite;
    
    console.log("rowCount: " + rowCount);
    var rowCourse = {count: rowCount, courseID: courseID, title:courseTitle, units: units};
            arrRowCount.push(rowCourse);
    
    var appendTr =
            '<tr id =tr' + rowCount + '>' +
            '<td>' + codeCourse
            + '<input type="hidden" name="codeCourse" class="readonlyWhite" value="' + codeCourse + '" />'
            + '<input type="hidden" name="courseID" class="readonlyWhite" value="' + courseID + '" />'
            + '</td>'
            + '<td>' + courseTitle
            + '<input type="hidden" name="title" class="readonlyWhite"  value="' + courseTitle + '" />'
            + '</td>'
            + '<td>' + units
            + '<input type="hidden" name="title" class="readonlyWhite"  value="' + units + '" />'
            + '</td>'
            + '<td><select name="yearLevel" class="form-control">';
    for (var x = 0; x < yearLevel.length; x++) {
        console.log("first: " + yearLevel[x].yearLevel + " vs " + yearLevelV);
        if (yearLevel[x].yearLevel == yearLevelV) {
            appendTr += '<option value=' + yearLevel[x].yearLevel + ' selected>' + yearLevel[x].yearLevel + '</option>';
        } else {
            appendTr += '<option value=' + yearLevel[x].yearLevel + '>' + yearLevel[x].yearLevel + '</option>';
        }
    }
    appendTr += '</select></td>';
    appendTr += '<td><select name="term" class="form-control">';
    for (var x = 0; x < term.length; x++) {
        if (term[x].term == termV) {
            appendTr += '<option value=' + termV + ' selected>' + termV + '</option>';
        } else {
            appendTr += '<option value=' + term[x].term + '>' + term[x].term + '</option>';
        }
    }
    appendTr += '</select></td>';
    appendTr += '<td><select name="prerequisite" class="form-control">';
    if (preRequisiteV == -1) {
        appendTr += "<option selected value=\"-1\"> -- select an option -- </option>"
        for (var x = 0; x < preRequisite.length; x++) {
            appendTr += '<option value=' + preRequisite[x].courseID + '>' + preRequisite[x].courseID.title + '</option>';
        }
    } else
    {
        for (var x = 0; x < preRequisite.length; x++) {
            if (preRequisite[x].courseID == preRequisiteV) {
                appendTr += '<option value=' + preRequisite[x].courseID + ' selected>' + preRequisite[x].title + '</option>';
            } else {
                appendTr += '<option value=' + preRequisite[x].courseID + '>' + preRequisite[x].title + '</option>';
            }
        }
    }
    appendTr += '</select></td>';
    appendTr += '<td><button title="delete" type="button" id="delete' + rowCount + '" class="btn btn-danger btn-xs"><i class="fa fa-trash" onClick="deleteRow(' + rowCount + ')"></i></button></td>';
    appendTr += '</tr>';
    
    totalUnits = totalUnits + parseInt(units);
    table.append(appendTr);
    rowCount++;
}