$(document).ready(function () {
    $('[data-toggle="popover"]').popover({html:true});
});
$('#curriculum-info').attr("title", "Curriculum Instructions");
$('#curriculum-info').attr("data-content", 
"1. Select a program from the list. A list of created curriculums will appear on the existing curriculums list. \n\
You have the option to choose an existing curriculum to import. <br /> \n\
2. After selecting a curriculum. Click the import button. The courses from the imported curriculum will appear below. \n\
Add new courses via search course list then click the add button.  <br /> \n\
3. Once finalized, click the confirm button to create the curriculum.  <br /><br /> \n\
*You can opt to create a curriculum from scratch by not using the import curriculum feature of the page.  <br /> \n\
*make sure to not leave any form blank. \n\
");
    