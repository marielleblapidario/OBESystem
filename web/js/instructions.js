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

$('#syllabus-info').attr("title", "Syllabus Instructions");
$('#syllabus-info').attr("data-content", 
"1. Select an existing curriculum that the syllabus will follow. \n\
The mapped outcome levels from the chosen curriculum will apply. <br /> \n\
2. Select a course from the course list that will be used to create the syllabus. <br /> \n\
3. Once a course is selected the \"Add Course Outcome\" form will appear below. <br /> \n\
When the save button is clicked the \"Add Assessments\" form will appear. \n\
4. When finally done with the assessments form, click the save button to finish creating the syllabus. \n\
");

$('#co-info').attr("title", "Course Outcome Definition");
$('#co-info').attr("data-content", 
"Knowledge, values and skills all learners are expected to demonstrate at the end of a course");

$('#assessment-info').attr("title", "Assessments Definition");
$('#assessment-info').attr("data-content", 
"-Represent the minimum performances that must be achieved to successfully complete a course. <br /> \n\
-Focus on the results of the learning experience \n\
");

$('#co-code').attr("title", "Reference code of the course outcome");
$('#co-description').attr("title", "e.g. Produce a strategic plan for a small manufacturing business");
$('#co-mapping').attr("title", "Map each course outcome to a performance indicator");

$('#as-code').attr("title", "Reference code of the assessment");
$('#as-type').attr("title", "assessment type");
$('#as-description').attr("title", "e.g. Quiz");
$('#as-mapping').attr("title", "Map each course outcome to a performance indicator");
$('#as-mapping').attr("title", "Map each assessment to a course outcome");
    