var dropDownCourse = $("#select-course");
var curriculumDropDown = $("#select-curriculum");
var termDropDown = $("#select-term");
var divCO = $("#div-addCO");
var divAssessment = $("#div-addAssessment");
var table = $("#data");
var tableA = $("#data-assessment");
var yearPicker = $('#startYear');
var arrRowCount = [];
var arrRowCountA = [];
var courseList = [];
var arrPI = [];
var arrCreateCO = [];
var arrCO = [];
var rowCount = 0;
var count = 0;
var rowCountA = 0;
var countA = 0;
var curriculumID;
var courseID;
var mapCurID;
var term;
var startYear;
var endYear;
var finalTerm;


$(document).ready(function () {
    divCO.hide();
    divAssessment.hide();
    getAllCurriculum();
    changeYear();
    getAllTerm();
    curriculumDropDown.change(function () {
        clearCO();
        courseList = [];
        curriculumID = curriculumDropDown.val();
        getAllCourse(curriculumID);
        arrPI = [];
    });
    dropDownCourse.change(function () {
        console.log("entered change course");
        clearCO();
        divCO.show();
        courseID = dropDownCourse.val();
        arrPI = [];
        getAllPI(curriculumID, courseID);
        $.ajax({
            type: "GET",
            url: "/OBESystem/GetMapCurID?curriculumID=" + curriculumID
                    + "&courseID=" + courseID,
            dataType: 'json',
            success: function (data) {
                console.log(data);
                mapCurID = data;
                $("#hidden-mapCurID").val(mapCurID);
            },
            error: function (response) {
                console.log(response);
            }
        });
    });
    termDropDown.change(function () {
        term = termDropDown.val;
    });
    addCO();
    AddAssessment();

    $('#save-btn').click(function () {
        var saveMapCurID = "" + mapCurID;
        finalTerm = $("[name='term']").val();
        console.log("finalTerm: ", finalTerm);
        startYear = $("[name='startYear']").val();
        endYear = $("[name='endYear']").val();
        var contributor = $("[name='contributor']").val();
        var arrCodeCO = $("[name='codeCO']").map(function () {
            return $(this).val();
        }).get();
        var arrDescription = $("[name='description']").map(function () {
            return $(this).val();
        }).get();
        var arrCodePI = $("[name='codePI']").map(function () {
            return $(this).val();
        }).get();
        var arrRemarks = $("[name='remarks']").map(function () {
            return $(this).val();
        }).get();

        for (var i = 0; arrCodeCO[i]; i++) {
            var createCO = {mapCurID: saveMapCurID, curriculumID: curriculumID, courseID: courseID,
                term: finalTerm, startYear: startYear, endYear: endYear, codeCO: arrCodeCO[i],
                description: arrDescription[i], codePI: arrCodePI[i], remarks: arrRemarks[i]};
            arrCreateCO.push(createCO);
        }
        var jsonData = JSON.stringify(arrCreateCO);

        $.ajax({
            type: "GET",
            url: "/OBESystem/EncodeSyllabus?mapCurID=" + mapCurID
                    + "&curriculumID=" + curriculumID + "&courseID=" + courseID
                    + "&term=" + finalTerm + "&startYear=" + startYear + "&endYear=" + endYear
                    + "&contributor=" + contributor,
            dataType: 'json',
            success: function (data) {
                console.log(data);
                arrCreateCO = [];
                $.ajax({
                    type: "POST",
                    url: "/OBESystem/EncodeCO",
                    dataType: 'json',
                    data: {'jsonData': jsonData},
                    success: function (data) {
                        console.log(data);
                        getAllCO();
                        console.log("arrCO size: " + arrCO.length);
                        divAssessment.show();
                    },
                    error: function (response) {
                        console.log(response);
                    }
                });
            },
            error: function (response) {
                console.log(response);
            }
        });
    });
});

function getAllPI(curriculumID, courseID) {
    console.log("curriculumID: " + curriculumID);
    console.log("courseID: " + courseID);
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetPIforCO?curriculumID=" + curriculumID
                + "&courseID=" + courseID,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            for (var x = 0; x < data.length; x++) {
                arrPI.push(data[x]);
            }
        },
        error: function (response) {
            console.log(response);
        }
    });
}


function getAllCurriculum() {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetAllCurriculum",
        dataType: 'json',
        success: function (data) {
            console.log(data);
            var a = "<option disabled selected value> -- select an option -- </option>";
            curriculumDropDown.append(a);
            data.forEach(showCurriculums);
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function getAllCourse(SelectedCurriculum) {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetSpecificMapCurriculumToCourse?SelectedCurriculum=" + SelectedCurriculum,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            for (var x = 0; x < data.length; x++) {
                var course = {courseID: data[x].courseID, courseTitle: data[x].courseTitle};
                courseList.push(course);

            }
            showCourses();
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function getAllCO() {
    console.log("curriculumID for CO: " + curriculumID);
    console.log("courseID for CO: " + courseID);
    console.log("term for CO: " + finalTerm);
    console.log("startYear for CO: " + startYear);
    console.log("endYear for CO: " + endYear);
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetAllCO?curriculumID=" + curriculumID
                + "&courseID=" + courseID + "&term=" + finalTerm
                + "&startYear=" + startYear + "&endYear=" + endYear,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            for (var x = 0; x < data.length; x++) {
                var co = {coID: data[x].coID, codeCO: data[x].codeCO};
                arrCO.push(co);
            }
            console.log("inside CO arrCO: " + arrCO.length);
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function addCO() {
    $('#addRowButton').click(function () {
        var newCodeCO;
        count += 1;
        if (count > 9) {
            newCodeCO = "CO-" + count;
        } else {
            newCodeCO = "CO-0" + count;
        }
        console.log(newCodeCO);
        console.log("rowCount: " + rowCount);
        arrRowCount.push(rowCount);

        var tr = document.createElement("tr");
        tr.id = 'tr' + rowCount;
        var codeCOCell = document.createElement("td");
        codeCOCell.innerHTML = newCodeCO
                + '<input type="hidden" name="codeCO" class="readonlyWhite" id="codeCO' + rowCount + '" value="' + newCodeCO + '" />';
        tr.appendChild(codeCOCell);

        var descriptionCell = document.createElement("td");
        descriptionCell.innerHTML = '<div class="col-sm-10"><input type="text" name="description" class="form-control no-border" id="description' + rowCount + '" required></div>';
        tr.appendChild(descriptionCell);

        var select = document.createElement("select");
        select.name = "codePI";
        tr.appendChild(select);
        for (var x = 0; x < arrPI.length; x++) {
            var option = document.createElement("option");
            option.label = arrPI[x].codePI;
            option.value = arrPI[x].codePI;
            select.appendChild(option);
        }

        var remarksCell = document.createElement("td");
        remarksCell.innerHTML = '<div class="col-sm-10"><input type="text" name="remarks" class="form-control no-border" id="remarks' + rowCount + '"></div>';
        tr.appendChild(remarksCell);

        var toolsCell = document.createElement("td");
        toolsCell.innerHTML = '<button type="button" id="edit' + rowCount + '" class="btn btn-success btn-xs"  onClick="makeRowEditable(' + rowCount + ')"><i class="fa fa-edit"> </i></button>' +
                '<button type="button" id="delete' + rowCount + '" class="btn btn-danger btn-xs"><i class="fa fa-trash" onClick="deleteRow(' + rowCount + ')"></i></button>';
        tr.appendChild(toolsCell);

        table.append(tr);
        rowCount++;
    });
}

function AddAssessment() {
    $('#add-assessment').click(function () {
        var newCodeAT;
        countA += 1;
        if (countA > 9) {
            newCodeAT = "AS-" + countA;
        } else {
            newCodeAT = "AS-0" + countA;
        }
        console.log(newCodeAT);
        console.log("rowCount: " + rowCountA);
        arrRowCountA.push(rowCountA);

        var tr = document.createElement("tr");
        tr.id = 'tr' + rowCountA;
        var codeATCell = document.createElement("td");
        codeATCell.innerHTML = newCodeAT
                + '<input type="hidden" name="codeAT" class="readonlyWhite" id="codeAT' + rowCountA + '" value="' + newCodeAT + '" />';
        tr.appendChild(codeATCell);

        var titleCell = document.createElement("td");
        titleCell.innerHTML = '<div class="col-sm-10"><input type="text" name="titleA" class="form-control no-border" id="titleA' + rowCountA + '" required></div>';
        tr.appendChild(titleCell);

        var select = document.createElement("select");
        select.name = "codeCOA";
        select.id = "codeCOA_" + rowCountA;
        tr.appendChild(select);
        for (var x = 0; x < arrCO.length; x++) {
            var option = document.createElement("option");
            option.label = arrCO[x].codeCO;
            option.value = arrCO[x].coID;
            select.appendChild(option);
        }
        var descriptionCell = document.createElement("td");
        descriptionCell.innerHTML = '<div class="col-sm-10"><input type="text" name="descriptionA" class="form-control no-border" id="descriptionA' + rowCountA + '" required></div>';
        tr.appendChild(descriptionCell);

        var weightCell = document.createElement("td");
        weightCell.innerHTML = '<div class="col-sm-10"><input type="number" name="weight" class="form-control no-border" id="weight_' + rowCountA + '" onblur= handleChange(this); required></div>';
        tr.appendChild(weightCell);

        var leftWeightCell = document.createElement("td");
        leftWeightCell.innerHTML = '<div class="col-sm-10"><input type="number" name="leftWeight" class="form-control no-border" id="leftWeight_' + rowCountA + '" max = "0" readOnly></div>';
        tr.appendChild(leftWeightCell);

        var toolsCell = document.createElement("td");
        toolsCell.innerHTML = '<button type="button" id="editA' + rowCountA + '" class="btn btn-success btn-xs"  onClick="makeRowEditable(' + rowCountA + ')"><i class="fa fa-edit"> </i></button>' +
                '<button type="button" id="deleteA' + rowCountA + '" class="btn btn-danger btn-xs"><i class="fa fa-trash" onClick="deleteRow(' + rowCountA + ')"></i></button>';
        tr.appendChild(toolsCell);

        tableA.append(tr);
        //var name = "#weight_" + rowCountA;
        $("#weight_" + rowCountA).blur(function () {
            var keyupWeight = this.id;
            var value = keyupWeight.split("_");
            var thisRowCountA = value[1];
            console.log("thisRowCountA: ", thisRowCountA);
            var selectedCO = $("#codeCOA_" + thisRowCountA).val();
            var temp = 100;
            var addWeight = 0;
            console.log("arrObj size: ", arrCO.length);
            console.log(arrCO);

            for (var c = 0; c < arrRowCountA.length; c++) {
                var storedCO = $("#codeCOA_" + arrRowCountA[c]).val();
                if (storedCO == selectedCO) {
                    console.log("entered if");
                    var toAdd = $("#weight_" + arrRowCountA[c]).val();
                    if (toAdd) {
                        console.log("to add: ", toAdd);
                        addWeight = parseInt(addWeight) + parseInt(toAdd);
                        console.log("add weight: ", addWeight);
                    }
                }
            }
            temp = temp - addWeight;
            console.log("temp: ", temp);
            if (temp < 0) {
                alert("invalid input!");
                var denied = $("#weight_" + thisRowCountA).val();
                console.log("denied:", denied);
                temp = temp + parseInt(denied);
                $("#weight_" + thisRowCountA).val(0);
            }
            for (var b = 0; b < arrRowCountA.length; b++) {
                var storedCO2 = $("#codeCOA_" + arrRowCountA[b]).val();
                if (storedCO2 == selectedCO) {
                    $("#leftWeight_" + arrRowCountA[b]).val(temp);
                }
            }
        });

        $("#codeCOA_" + rowCountA).change(function () {
            var keyupWeight = this.id;
            var value = keyupWeight.split("_");
            var thisRowCountA = value[1];
            console.log("thisRowCountA: ", thisRowCountA);
            var selectedCO = $("#codeCOA_" + thisRowCountA).val();
            var temp = 100;
            var addWeight = 0;
            console.log("arrObj size: ", arrCO.length);
            console.log(arrCO);

            for (var c = 0; c < arrRowCountA.length; c++) {
                var storedCO = $("#codeCOA_" + arrRowCountA[c]).val();
                if (storedCO == selectedCO) {
                    console.log("entered if");
                    var toAdd = $("#weight_" + arrRowCountA[c]).val();
                    if (toAdd) {
                        console.log("to add: ", toAdd);
                        addWeight = parseInt(addWeight) + parseInt(toAdd);
                        console.log("add weight: ", addWeight);
                    }
                }
            }
            temp = temp - addWeight;
            console.log("temp: ", temp);
            if (temp < 0) {
                alert("invalid input!");
                var denied = $("#weight_" + thisRowCountA).val();
                console.log("denied:", denied);
                temp = temp + parseInt(denied);
                $("#weight_" + thisRowCountA).val(0);
            }
            for (var b = 0; b < arrRowCountA.length; b++) {
                var storedCO2 = $("#codeCOA_" + arrRowCountA[b]).val();
                if (storedCO2 == selectedCO) {
                    $("#leftWeight_" + arrRowCountA[b]).val(temp);
                }
            }
        });
        rowCountA++;
    });
}

function showCourses() {
    dropDownCourse.find('option').remove().end();
    var a = "<option disabled selected value> -- select an option -- </option>";
    dropDownCourse.append(a);
    for (var x = 0; x < courseList.length; x++) {
        var s = "<option value=" + courseList[x].courseID + ">" + courseList[x].courseTitle + "</option>";
        dropDownCourse.append(s);
    }
}

function showCurriculums(data) {
    var s = "<option value = " + data.codeCurriculum + ">" + data.title + "</option>";
    curriculumDropDown.append(s);
}

function makeRowEditable(count) {
    var description = 'description' + count;
    var remarks = 'remarks' + count;

    if (document.getElementById(description).readOnly) {
        document.getElementById(description).readOnly = false;
        document.getElementById(remarks).readOnly = false;
    } else {
        document.getElementById(description).readOnly = true;
        document.getElementById(remarks).readOnly = true;
    }
}

function deleteRow(num) {
    var retVal = confirm("Are you sure you want to delete this row?");
    if (retVal === true) {
        var tr = 'tr' + num;
        document.getElementById(tr).remove();
        count--;
        return true;
    } else {
        console.log("cancelled");
        return false;
    }
}

function getAllTerm() {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetAllTrimester",
        dataType: 'json',
        success: function (data) {
            console.log(data);
            data.forEach(showTerm);
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function showTerm(data) {
    var s = "<option value = " + data.term + ">" + data.term + "</option>";
    termDropDown.append(s);
}


function changeYear() {
    yearPicker.on("input change", function (e) {
        console.log("changed: ", e.target.value);
        var sYear = +e.target.value;
        var eYear = sYear + 1;
        $("#endYear").val(eYear);
    });
}

function clearCO() {
    console.log("arrRowCount size: " + arrRowCount.length);
    for (var x = 0; x < arrRowCount.length; x++) {
        console.log("arrRowCount count: " + arrRowCount[x]);
        var tr = 'tr' + arrRowCount[x];
        document.getElementById(tr).remove();
        arrRowCount.splice(x, 1);
    }
    console.log("arrRowCount length: " + arrRowCount.length);
    rowCount = 0;
    count = 0;
    divCO.hide();
}

function handleChange(input) {
    if (input.value < 0)
        input.value = 0;
    if (input.value > 100)
        input.value = 100;
}