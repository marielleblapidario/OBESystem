var offeringID = sessionStorage.getItem("offeringID");
var syllabusID = sessionStorage.getItem("syllabusID");
var curriculum = $('#curriculum-title');
var course = $('#course-title');
var hiddenCurriculum = $('#hidden-curriculum-title');
var hiddenCourse = $('#hidden-course-title');
var hiddenSyllabus = $('#hidden-syllabusID');
var term = $('#term');
var startYear = $('#startYear');
var endYear = $('#endYear');
var section = $('#section');
var days = $('#days');
var time = $('#time');
var table = $('#data');
var divTable = $('#div-students');
var faculty = $('#faculty');
var arrEnrolledStudents = [];
var arrAssessment = [];
var arrTypes = [];
var arrGrades = [];
var arrGradesDisplay = [];
var arrStudentData = [];
var arrCOGrades = [];
var arrCO = [];
var strSection;
var strTerm;
var strCourse;
var strYear;
var modal = $('#modal-co');

$(window).load(function () {
    divTable.hide();
    console.log("offeringID: " + offeringID);
    console.log("syllabusID: " + syllabusID);
    getSyllabus(offeringID);
    getEnrolledStudents(offeringID);

    $('#grades-format').click(function () {
        convertToCSV();
        convertToCSVsimple();
    });
    $('#students-format').click(function () {
        convertToCSVsimple();
    });
    $('#grades-upload').click(function () {
        this.value = null;
    });
    $('#students-upload').click(function () {
        this.value = null;
    });
    document.getElementById('students-upload').addEventListener('change', xmlToJson, false);

    document.getElementById('grades-upload').addEventListener('change', uploadAssessments, false);
    saveStudents();
    saveGrades();
});

function getSyllabus(offeringID) {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetSpecificOffering?offeringID=" + offeringID,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            curriculum.val(data.curriculumTitle);
            hiddenCurriculum.val(data.curriculumID);
            hiddenSyllabus.val(data.syllabusID);
            course.val(data.courseTitle);
            hiddenCourse.val(data.courseID);
            startYear.val(data.startYear);
            endYear.val(data.endYear);
            term.val(data.term);
            section.val(data.section);
            strSection = data.section;
            days.val(data.days);
            time.val(data.time);
            faculty.val(data.facultyName);

            strYear = data.startYear;
            strTerm = data.term;
            strCourse = data.codeCourse;
        },
        error: function (response) {
            console.log(response);
        }
    });
}

//get all enrolled students
function getEnrolledStudents(offeringID) {
    arrEnrolledStudents = [];
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetEnrolledStudents?offeringID=" + offeringID,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            //pushed the array of student object
            for (var x = 0; x < data.length; x++) {
                var student = {studentID: data[x].studentID, lastName: data[x].lastName,
                    firstName: data[x].firstName, middleName: data[x].middleName};
                arrEnrolledStudents.push(student);
            }
            getAssessments(syllabusID);
            getTypes(syllabusID);
            getCO(syllabusID);
        },
        error: function (response) {
            console.log(response);
        }
    });
}

//get all assessments under the course
function getAssessments(syllabusID) {
    arrAssessment = [];
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetAssessmentForFormat?syllabusID=" + syllabusID,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            //pushed the array of Strings (e.g. AS - 01, AS -02, .....
            console.log(data.length);
            for (var x = 0; x < data.length; x++) {
                arrAssessment.push(data[x]);
            }
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function getCO(syllabusID) {
    arrCO = [];
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetCOforFormat?syllabusID=" + syllabusID,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            //pushed the array of Strings (e.g. AS - 01, AS -02, .....
            console.log(data.length);
            for (var x = 0; x < data.length; x++) {
                arrCO.push(data[x]);
            }
        },
        error: function (response) {
            console.log(response);
        }
    });
}

//get all types under the syllabus
function getTypes(syllabusID) {
    arrType = [];
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetTypeForFormat?syllabusID=" + syllabusID,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            //pushed the array of Strings (e.g. AS - 01, AS -02, .....
            console.log(data.length);
            for (var x = 0; x < data.length; x++) {
                arrType.push(data[x]);
            }
            getGrades(offeringID);
            getCoGrades(offeringID);
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function getGrades(offeringID) {
    arrGradesDisplay = [];
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetGradesOfSection?offeringID=" + offeringID,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            for (var x = 0; x < data.length; x++) {
                var grade = {studentID: data[x].studentID, codeAT: data[x].codeAT, grade: data[x].grade};
                arrGradesDisplay.push(grade);
            }
            console.log("tableHeader check arrStudentData size: " + arrEnrolledStudents.length);
            console.log("getGrades check arrGradesDisplay size: " + arrGradesDisplay.length);
            if (arrEnrolledStudents.length > 0) {
                console.log("entered first if");
                table.empty();
                tableHeader();
                divTable.show();
            }
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function getCoGrades(offeringID) {
    arrCOGrades = [];
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetAllgradesCO?offeringID=" + offeringID,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            for (var x = 0; x < data.length; x++) {
                arrCOGrades.push(data[x]);
            }
            console.log("getGrades check arrCOGrades size: " + arrCOGrades.length);
            if(arrEnrolledStudents.length > 0){
                modal.empty();
                modalHeader();
            }
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function convertToCSVsimple() {
    var CSV = '(1) Input grades in DLSU grading format. (e.g. 4, 3.5, 3, 2.5, 2, 1.5, 1 & 0).\r\n' +
            '(2) If you have multiple assessments under one assessment category, compute the final grade under the assessment category by averaging all the assessments given under the category (e.g. Quiz-1: 2.5, Quiz-2: 3.0, Quiz-3: 4.0; Computation (2.5 + 3 + 4.0)/3 = 3.16, round down/up, final grade 3.0).';


    if (CSV == '') {
        alert("Invalid data");
        return;
    }

    //Generate a file name
    var fileName = "instruction";

    //Initialize file format you want csv or xls
    var uri = 'data:text/csv;charset=utf-8,' + escape(CSV);

    //this trick will generate a temp <a /> tag
    var link = document.createElement("a");
    link.href = uri;

    //set the visibility hidden so it will not effect on your web-layout
    link.style = "visibility:hidden";
    link.download = fileName + ".text";

    //this part will append the anchor tag and remove it after automatic click
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
}

function convertToCSV() {
    var arrData = typeof arrEnrolledStudents != 'object' ? JSON.parse(arrEnrolledStudents) : arrEnrolledStudents;
    var CSV = '';

    var ShowLabel = true;
    //This condition will generate the Label/Header
    if (ShowLabel) {
        var row = "";

        //This loop will extract the label from 1st index of arrEnrolledStudents
        for (var index in arrData[0]) {

            //Now convert each value to string and comma-seprated
            row += index + ',';
        }
        var x;
        //Added by Raji, this loop adds headers from arrType
        for (x in arrType)
        {
            row += arrType[x] + ',';
        }

        row = row.slice(0, -1);


        //append Label row with line break
        CSV += row + '\r\n';
    }

    //1st loop is to extract each row
    for (var i = 0; i < arrData.length; i++) {
        var row = "";

        //2nd loop will extract each column and convert it in string comma-seprated
        for (var index in arrData[i]) {
            row += '"' + arrData[i][index] + '",';
        }

        row.slice(0, row.length - 1);

        //add a line break after each row
        CSV += row + '\r\n';
    }

    if (CSV == '') {
        alert("Invalid data");
        return;
    }

    //Generate a file name
    var fileName = "grades_" + strCourse + "_" + strYear + "_" + strTerm + "_" + strSection;

    //Initialize file format you want csv or xls
    var uri = 'data:text/csv;charset=utf-8,' + escape(CSV);

    //this trick will generate a temp <a /> tag
    var link = document.createElement("a");
    link.href = uri;

    //set the visibility hidden so it will not effect on your web-layout
    link.style = "visibility:hidden";
    link.download = fileName + ".csv";

    //this part will append the anchor tag and remove it after automatic click
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
}

// Method that checks that the browser supports the HTML5 File API
function browserSupportFileUpload()
{
    var isCompatible = false;
    if (window.File && window.FileReader && window.FileList && window.Blob)
    {
        isCompatible = true;
    }
    return isCompatible;
}

function setJsonObj(xml) {
    var js_obj = {};

    if (xml.nodeType == 1) {
        if (xml.attributes.length > 0) {
            js_obj["@attributes"] = {};
            for (var j = 0; j < xml.attributes.length; j++) {
                var attribute = xml.attributes.item(j);
                js_obj["@attributes"][attribute.nodeName] = attribute.value;
            }
        }
    } else if (xml.nodeType == 3) {
        js_obj = xml.nodeValue;
    }
    if (xml.hasChildNodes()) {
        for (var i = 0; i < xml.childNodes.length; i++) {
            var item = xml.childNodes.item(i);
            var nodeName = item.nodeName;
            var string = "i : " + i + ", item : " + setJsonObj(item) + ", nodeName : " + nodeName + ", xml.nodeType : " + xml.nodeType;
            // console.log(string);
            if (typeof (js_obj[nodeName]) == "undefined") {
                js_obj[nodeName] = setJsonObj(item);
            } else {
                if (typeof (js_obj[nodeName].push) == "undefined") {
                    var old = js_obj[nodeName];
                    js_obj[nodeName] = [];
                    js_obj[nodeName].push(old);
                }
                js_obj[nodeName].push(setJsonObj(item));
            }
        }
    }
    return js_obj;
}

function jsontoStr(js_obj) {
    var rejsn = JSON.stringify(js_obj, undefined, 2).replace(/(\\t|\\r|\\n)/g, '').replace(/"",[\n\t\r\s]+""[,]*/g, '').replace(/(\n[\t\s\r]*\n)/g, '').replace(/[\s\t]{2,}""[,]{0,1}/g, '').replace(/"[\s\t]{1,}"[,]{0,1}/g, '').replace(/\[[\t\s]*\]/g, '""');
    return (rejsn.indexOf('"parsererror": {') == -1) ? rejsn : 'Invalid XML format';
}

function xmlToJson(evt)
{
    arrStudentData = [];
    if (!browserSupportFileUpload())
    {
        alert('The File APIs are not fully supported in this browser!');
    } else
    {
        var data = null;
        var file = evt.target.files[0];
        var reader = new FileReader();
        reader.readAsText(file);
        reader.onload = function (event)
        {
            var xml = event.target.result;
            xml = xml.replace(/>'/g, ">");
            xml = xml.replace(/<\/TD>\n<TR>/g, "</TD></TR>\n<TR>");
            xml = xml.replace(/<\/TD>\n<\/TABLE>/g, "</TD></TR>\n</TABLE>");
            xml = xml.replace(/",/g, "\" ");
            xml = xml.replace(/&nbsp;/g, "Status");
            console.log(xml);
            if (window.DOMParser) {
                var getxml = new DOMParser();
                var xmlDoc = getxml.parseFromString(xml, "text/xml");
            } else {
                // for Internet Explorer
                var xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
                xmlDoc.async = "false";
            }

            // gets the JSON string
            var json_str = jsontoStr(setJsonObj(xmlDoc));
            var jason = JSON.parse(json_str);

            // sets and returns the JSON object, if "rstr" undefined (not passed), else, returns JSON string\
            var headers = jason["TABLE"]["TR"][0]["TH"];
            var header_names = [];
            var head_len = headers.length;
            for (var i = 0; i < head_len; i++)
            {
                var str_header = headers[i]["#text"];
                str_header = str_header.replace(/ /g, "_");
                str_header = str_header.replace(/\./g, '');
                header_names.push(str_header);
            }

            var students_arr = jason["TABLE"]["TR"]
            var students_len = students_arr.length;
            var students = [];

            for (var i = 1; i < students_len; i++)
            {
                var obj = {};
                for (var j = 0; j < head_len; j++)
                {
                    obj[header_names[j]] = students_arr[i]["TD"][j]["#text"];
                }
                students.push(obj);
            }

            console.log(students);

            //START POST FUNCTION HERE:
            for (var i = 0; i < students.length; i++) {
                var studentData = {studentID: students[i].ID_No, offeringID: offeringID};
                arrStudentData.push(studentData);
                console.log("studentID", students[i].ID_No);
            }
            console.log(arrStudentData);
            //END POST FUNCTION HERE
        };
        reader.onerror = function ()
        {
            alert('Unable to read ' + file.fileName);
        };
    }
}


// Method that reads and posts the students csv file
function uploadStudents(evt)
{
    arrStudentData = [];
    if (!browserSupportFileUpload())
    {
        alert('The File APIs are not fully supported in this browser!');
    } else
    {
        var data = null;
        var file = evt.target.files[0];
        var reader = new FileReader();
        reader.readAsText(file);
        reader.onload = function (event)
        {
            var csv = event.target.result;
            var lines = csv.split("\n");
            var headers = lines[0].split(",");

            for (var i = 1; i < lines.length; i++)
            {
                var currentline = lines[i].split(",");
                for (var j = 0; j < headers.length; j++)
                {
                    var id = currentline[j].replace('\r', '').replace('\n', '');
                    var studentData = {studentID: id, offeringID: offeringID};
                    arrStudentData.push(studentData);
                }
            }
            //result is the resulting object
            console.log(arrStudentData);
        };
        reader.onerror = function ()
        {
            alert('Unable to read ' + file.fileName);
        };
    }
}

// Method that reads and posts the assessments csv file
function uploadAssessments(evt)
{
    arrAssessments = [];
    arrGrades = [];
    if (!browserSupportFileUpload())
    {
        alert('The File APIs are not fully supported in this browser!');
    } else
    {
        var data = null;
        var file = evt.target.files[0];
        var reader = new FileReader();
        reader.readAsText(file);
        reader.onload = function (event)
        {
            var csv = event.target.result;
            var lines = csv.split("\n");
            var headers = lines[0].split(",");
            var studentData = [];
            //get studentData
            for (var i = 1; i < lines.length; i++)
            {
                var obj = {};
                var currentline = lines[i].split(",");
                for (var j = 0; j < 4; j++)
                {
                    obj[headers[j]] = currentline[j];
                }
                studentData.push(obj);
            }
            console.log("studentData: ");
            console.log(studentData);
            //get grades of students
            for (var i = 1; i < lines.length; i++)
            {
                var currentline = lines[i].split(",");
                for (var j = 4; j < headers.length; j++)
                {
                    if (studentData[i - 1].studentID) {
                        var tempType = headers[j].replace('\r', '').replace('\n', '');
                        var tempGrade = currentline[j].replace('\r', '').replace('\n', '');
                        var grades = {studentID: studentData[(i - 1)].studentID,
                            offeringID: offeringID, type: tempType, grade: tempGrade,
                            syllabusID: syllabusID};
                        arrGrades.push(grades);
                    }
                }
            }
            console.log("arrGrades: ");
            console.log(arrGrades);
        };
        reader.onerror = function ()
        {
            alert('Unable to read ' + file.fileName);
        };
    }
}

function saveStudents() {
    $('#students-save').click(function () {
        if (arrStudentData) {
            var jsonData = JSON.stringify(arrStudentData);
            $.ajax({
                type: "POST",
                url: "/OBESystem/EncodeEnrolledStudents",
                dataType: 'json',
                data: {'jsonData': jsonData},
                success: function (data) {
                    console.log(data);
                    alert("success!");
                    getEnrolledStudents(offeringID);
                },
                error: function (response) {
                    console.log(response);
                    alert("something went wrong in saving.");
                }
            });
        } else {
            alart("empty or no file uploaded");
        }
    });
}

function saveGrades() {
    $('#grades-save').click(function () {
        if (arrGrades) {
            var jsonData = JSON.stringify(arrGrades);
            $.ajax({
                type: "POST",
                url: "/OBESystem/EncodeStudentGrades",
                dataType: 'json',
                data: {'jsonData': jsonData},
                success: function (data) {
                    console.log(data);
                    alert("success!");
                    getEnrolledStudents(offeringID);
                },
                error: function (response) {
                    console.log(response);
                    alert("something went wrong in saving.");
                }
            });
        } else {
            alart("empty or no file uploaded");
        }
    });
}

function tableHeader() {
    console.log("entered tableHeader");
    var tr = "<tr id='table-header'>";
    table.append(tr);
    var header = $("#table-header");
    header.append("<th>Student ID</th>");
    header.append("<th>Last Name</th>");
    header.append("<th>First Name</th>");
    header.append("<th>Middle Name</th>");

    for (var x = 0; x < arrAssessment.length; x++) {
        var a = "<th>" + arrAssessment[x] + " </th>";
        header.append(a);
    }
    header.append("</tr>");
    tableRow();
}

function tableRow() {
    console.log("entered tableRow");
    for (var x = 0; x < arrEnrolledStudents.length; x++) {
        if (arrEnrolledStudents.studentID != null || arrEnrolledStudents.studentID != '') {
            var s = '<tr id= studentTr' + x + '>'
                    + '<td>' + arrEnrolledStudents[x].studentID + '</td>'
                    + '<td>' + arrEnrolledStudents[x].lastName + '</td>'
                    + '<td>' + arrEnrolledStudents[x].firstName + '</td>'
                    + '<td>' + arrEnrolledStudents[x].middleName + '</td>';
            table.append(s);
            var row = $('#studentTr' + x);
            for (var a = 0; a < arrAssessment.length; a++) {
                if (arrGradesDisplay.length > 0) {
                    for (var b = 0; b < arrGradesDisplay.length; b++) {
                        if (arrEnrolledStudents[x].studentID == arrGradesDisplay[b].studentID &&
                                arrAssessment[a] == arrGradesDisplay[b].codeAT) {
                            var c = '<td>' + arrGradesDisplay[b].grade + '</td>';
                            row.append(c);
                        }
                    }
                } else {
                    var y = '<td></td>';
                    row.append(y);
                }
            }
            table.append("</tr>");
        }
    }
}

function modalHeader() {
    console.log("entered modalHeader");
    var tr = "<tr id='modal-header'>";
    modal.append(tr);
    var header = $("#modal-header");
    header.append("<th>Student ID</th>");
    header.append("<th>Last Name</th>");
    header.append("<th>First Name</th>");
    header.append("<th>Middle Name</th>");

    for (var x = 0; x < arrCO.length; x++) {
        var a = "<th>" + arrCO[x] + " </th>";
        header.append(a);
    }
    header.append("</tr>");
    modalRow();
}

function modalRow() {
    console.log("entered modalRow");
    for (var x = 0; x < arrEnrolledStudents.length; x++) {
        if (arrEnrolledStudents.studentID != null || arrEnrolledStudents.studentID != '') {
            var s = '<tr id= modalTr' + x + '>'
                    + '<td>' + arrEnrolledStudents[x].studentID + '</td>'
                    + '<td>' + arrEnrolledStudents[x].lastName + '</td>'
                    + '<td>' + arrEnrolledStudents[x].firstName + '</td>'
                    + '<td>' + arrEnrolledStudents[x].middleName + '</td>';
            modal.append(s);
            var row = $('#modalTr' + x);
            for (var a = 0; a < arrCO.length; a++) {
                if (arrCOGrades.length > 0) {
                    for (var b = 0; b < arrCOGrades.length; b++) {
                        console.log('compare id: '+ arrEnrolledStudents[x].studentID
                                + ' vs ' + arrCOGrades[b].studentID +" and "+
                                arrCO[a] + " vs " + arrCOGrades[b].codeCO);
                        if (arrEnrolledStudents[x].studentID == arrCOGrades[b].studentID &&
                                arrCO[a] == arrCOGrades[b].codeCO) {
                            console.log('entered if');
                            var c = '<td>' + arrCOGrades[b].gradeCO + '</td>';
                            row.append(c);
                        }
                    }
                } else {
                    var y = '<td></td>';
                    row.append(y);
                    console.log('entered else');
                }
            }
            modal.append("</tr>");
        }
    }
}