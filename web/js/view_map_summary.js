var table = $("#data");
var programCode = sessionStorage.getItem("programCode");
var arrIGA = [];
var arrPA = [];
var arrPO = [];
var arrPI = [];

$(document).ready(function () {
    getAllIGA();

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
            getAllPA(programCode);
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
            getAllPO(program);
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
            getAllPI(program);
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
            console.log("arrIGA : ", arrIGA.length);
            console.log("arrPA : ", arrPA.length);
            console.log("arrPO : ", arrPO.length);
            console.log("arrPI : ", arrPI.length);
            create_table(arrIGA, arrPA, arrPO, arrPI, 'data');
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function create_table(IGA, PA, PO, PI, table_name)
{

var iga_len     = IGA.length; //IGA is the array of IGAs
var pa_len      = PA.length; //PA is the array of PAs
var po_len      = PO.length; //PO is the array of POS
var pi_len      = PI.length; //PI is the array of PIs
var ret_arr     = [];
var current_iga = 0;
var current_pa  = 0;
var current_pi  = 0;
var current_po  = 0;
var counter     = 0;
var iga_rows    = 0;
var pa_rows     = 0;
var pi_rows     = 0;
var po_rows     = 0;
var is_after       = false;

for(var i = 0; i < iga_len; i++)
{
    current_iga     = counter;
    counter++;

    iga_rows    = 0;
    pa_rows     = 0;
    pi_rows     = 0;
    po_rows     = 0;
	iga_rows++;

    ret_arr.push({
        "value"     : IGA[i]["codeIGA"],
        "rowspan"   : 0,
        "column"    : "IGA"
    });

	pa_rows = 0;
    for(var j = 0; j < pa_len; j++)
    {
        if(PA[j]["codeIGA"] == IGA[i]["codeIGA"])
        {
            pa_rows++;
            current_pa = counter;
            counter++;
            ret_arr.push({
                "value"     : PA[j]["codePA"],
                "rowspan"   : 0,
                "column"    : "PA"
            });
            po_rows = 0;
            for(var k = 0; k < po_len; k++)
            {
                if(PO[k]["codePA"] == PA[j]["codePA"])
                {
                    current_po = counter;
                    counter++;
                    po_rows++;
                    ret_arr.push({
                            "value"     : PO[k]["codePO"],
                            "rowspan"   : 0,
                            "column"    : "PO"
                    });
                    pi_rows = 0;
                    for(var l = 0; l < pi_len; l++)
                    {
                        if(PI[l]["codePO"] == PO[k]["codePO"])
                        {
                            counter++;
                            pi_rows++;
                            ret_arr.push({
                                    "value"     : PI[l]["codePI"],
                                    "rowspan"   : 1,
                                    "column"    : "PI"
                            });
                        }
                    }
					if(pi_rows > 0)
					{
						ret_arr[current_po]["rowspan"]      = pi_rows;
	                    ret_arr[current_pa]["rowspan"]      += pi_rows;
	                    ret_arr[current_iga]["rowspan"]     += pi_rows;
					}
					else
					{
						ret_arr[current_po]["rowspan"]      = 1;
	                    ret_arr[current_pa]["rowspan"]      += 1;
	                    ret_arr[current_iga]["rowspan"]     += 1;
					}
                }
            }
			if(po_rows == 0)
			{
				ret_arr[current_pa]["rowspan"]      = 1;
				ret_arr[current_iga]["rowspan"]     += 1;
			}
        }
    }
	if(pa_rows == 0)
	{
		ret_arr[current_iga]["rowspan"]     = 1;
	}
}
var order = {
        "IGA" : {
            "before"    : "PI",
            "after"     : "PA"
        },
        "PA" : {
            "before"    : "IGA",
            "after"     : "PO"
        },
        "PO" : {
            "before"    : "PA",
            "after"     : "PI"
        },
        "PI" : {
            "before"    : "PO",
            "after"     : "IGA"
        }
};
var table_string 	= "";
var ret_len 		= ret_arr.length;
var is_tr 			= true;
var td_count        = 0;
var close_tr        = false;
for(var i = 0; i < ret_len; i++)
{
    if(close_tr)
    {
        table_string += "</tr><tr>";
        close_tr = false;
    }
	if(ret_arr[i]["column"] == "IGA" && is_tr == false)
    {
        table_string += "</tr>";
        is_tr 		= true;
        // break;
    }
    if(ret_arr[i]["column"] == "IGA" && is_tr == true)
    {
        table_string    += "<tr>";
        is_tr           = false;
    }
    if(td_count == 4)
    {
        table_string += "</tr><tr>";
        td_count    = 0;
    }
    else
    {
        if(i != 0 && order[ret_arr[i]["column"]]["before"] != ret_arr[i - 1]["column"] && is_after == false)
        {
            table_string += "</tr><tr>";
            td_count = 0;
        }
    }

    // if(ret_arr[i]["column"] == "PI" && td_count == 0)
    // {
    //     table_string += "</tr><tr>";
    //     close_tr = true;
    // }
    if(ret_arr[i]["column"] != "PI")
    {
        table_string += "<td rowspan='"+ ret_arr[i]["rowspan"] +"'>" + ret_arr[i]["value"] + "</td>";
    }
    else
    {
        table_string += "<td>" + ret_arr[i]["value"] + "</td>";
    }
    if(is_after == true)
    {
        is_after = false;
    }
    if(td_count != 4)
    {
        if(i != ret_len - 1 && order[ret_arr[i]["column"]]["after"] != ret_arr[i + 1]["column"] && ret_arr[i]["column"] != "PI" && ret_arr[i + 1]["column"] != "IGA")
        {
            table_string += "</tr><tr>";
            td_count = 0;
            is_after    = true;
        }
    }
    td_count++;
    if(i == ret_len - 1)
    {
        table_string += "</tr><tr>";
    }
}
table_name = "#" + table_name;
$(table_name).find('tbody').append(table_string);

}
