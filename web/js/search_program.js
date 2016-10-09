var programDropDown = $("#select-program");
var collegeDropDown = $("#select-college");

getAllCollege();
getAllProgram();

$(document).ready(function () {

    programDropDown.change(function () {
        var program = $('#select-program option:selected').val();
        getCollegeByProgram(program);

    }
    );
    collegeDropDown.change(function () {
        var college = $('#select-college option:selected').val();
        getProgramByCollege(college);

    }
    );

    $('#confirm-btn').click(function () {
        var programTitle = $('#select-program option:selected').text();
        var programCode = $('#select-program option:selected').val();

        var college = $('#select-college option:selected').text();
        var collegeID = $('select-college option:selected').val();

        sessionStorage.setItem("programTitle", programTitle);
        var s = sessionStorage.getItem("programTitle");
        sessionStorage.setItem("programCode", programCode);
        var ss = sessionStorage.getItem("programCode");
        sessionStorage.setItem("college", college);
        var sss = sessionStorage.getItem("college");
        sessionStorage.setIteam("collegeID", collegeID);
        var ssss = sessionStorage.getItem("collegeID");

        console.log('title: ' + s + ' code: ' + ss + " college: " + sss + "collegeID: " + ssss);
    });
}

);

function getAllCollege() {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetAllColleges",
        dataType: 'json',
        success: function (data) {
            console.log(data);
            var s = "<option disabled selected value> -- select an option -- </option>";
            collegeDropDown.append(s);
            data.forEach(addCollege);
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function getAllProgram() {
    $.ajax({
        type: "GET",
        url: "/OBESystem/ViewProgramList",
        dataType: 'json',
        success: function (data) {
            console.log(data);
            var s = "<option disabled selected value> -- select an option -- </option>";
            programDropDown.append(s);
            data.forEach(addProgram);
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function getCollegeByProgram(program) {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetCollegeByProgram?SelectedProgram=" + program,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            $('#select-college').find('option').remove().end();
            var s = "<option value = " + data.college + ">" + data.collegeName + "</option>";
            collegeDropDown.append(s);
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function getProgramByCollege(college) {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetProgramByCollege?SelectedCollege=" + college,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            $('#select-program').find('option').remove().end();
            data.forEach(addProgram);
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function addProgram(data) {
    console.log("Entered addProgram: " + data.codeProgram);
    var s = "<option value = " + data.codeProgram + ">" + data.title + "</option>";
    programDropDown.append(s);
}

function addCollege(data) {
    console.log("Entered addCollege: " + data.college);
    var s = "<option value = " + data.collegeID + ">" + data.college + "</option>";
    collegeDropDown.append(s);
}




