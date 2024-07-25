var testoErrore= $("#testo-errore");
var messaggioErrore="<br/>Inserisci tutti i campi correttamente";

function controllaEmailLogin()
{
    const email= $("#emailLogin").val();
    if(email.length<=5)
    {
        return false;
    }
    return true;
}

function controllaPswLogin()
{
    const psw= $("#pswLogin").val();
    if(psw.length<4)
    {
        return false;
    }
    return true;
}

$("#loginBtn").click(function ()
{
    //controllo della email

    if(controllaEmailLogin()==false)
    {
        $("#messaggioErrore").fadeIn();
        $("#emailLogin").css("border", " 1px solid red");
        messaggioErrore+="<br/>Email non valida";
    }
    else
    {
        $("#emailLogin").css("border", " 1px solid green");
    }

    //controllo della psw

    if(controllaPswLogin()==false)
    {
        $("#messaggioErrore").fadeIn();
        $("#pswLogin").css("border", " 1px solid red");
        messaggioErrore+="<br/>Password non valida";
    }
    else
    {
        $("#pswLogin").css("border", " 1px solid green");
    }

    testoErrore.html(messaggioErrore);

    if(messaggioErrore.length<1)
    {
        $("#messaggioErrore").fadeOut();
        $("#pswLogin").css("border", " 1px solid green");
    }

    messaggioErrore="";

    const url="footer.jsp";
    $(location).attr('href',url);
});
   