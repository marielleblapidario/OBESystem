var table = $("#data");
var programCode = sessionStorage.getItem("programCode");
var arrIGA = [];
var arrPA = [];
var arrPO = [];
var arrPI = [];

$(document).ready(function () {
    getAllIGA();
    getAllPA(programCode);
    getAllPO(programCode);
    getAllPI(programCode);
});

function getAllIGA() {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetAllIGA",
        dataType: 'json',
        success: function (data) {
            console.log(data);
            for (var x = 0; x < data.length; x++) {
                arrIGA.push(data[x]);
            }
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function getAllPA(program) {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetAllPA?SelectedProgram=" + program,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            for (var x = 0; x < data.length; x++) {
                arrPA.push(data[x]);
            }
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function getAllPO(program) {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetAllPO?SelectedProgram=" + program,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            for (var x = 0; x < data.length; x++) {
                arrPO.push(data[x]);
            }
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function getAllPI(program) {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetAllPI?SelectedProgram=" + program,
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

function createTable(){
    for(var a  = 0; a < arrIGA.length; a++){
        for(var b = 0; b < arrPA.length; b++){
            for(var c = 0; c < arrPO.length; c++){
                for(var d = 0; d < arrPI.length; c++){
                    var content = "";
                    var tr = "";
                }  
            }
        }
    }
}
