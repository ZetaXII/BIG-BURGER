$("#formRegistra").css("display", "none");
$("#formAccedi").css("display", "block");
$("#btnAccedi").css("display", "none");

$( "#btnRegistra" ).click(function()
{
    $("#messaggioErrore").fadeOut();
    $("#formAccedi").fadeOut("fast");
    $("#btnRegistra").fadeOut("fast");
    $( "#formRegistra" ).fadeIn("slow");
    $( "#btnAccedi" ).fadeIn("slow");
});

$( "#btnAccedi" ).click(function()
{
    $("#messaggioErrore").fadeOut();
    $("#formRegistra").fadeOut("fast");
    $("#btnAccedi").fadeOut("fast");
    $( "#formAccedi" ).fadeIn("slow");
    $( "#btnRegistra" ).fadeIn("slow");
});