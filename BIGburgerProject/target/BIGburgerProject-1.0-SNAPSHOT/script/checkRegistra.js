var testoErrore= $("#testo-errore");
var messaggioErrore="<br/>Inserisci tutti i campi correttamente";

function controllaNomeRegistra()
{
    const nome= $("#nome").val();
    if(nome.length<=2)
    {
        return false;
    }
    return true;
}

function controllaCognomeRegistra()
{
    const cognome= $("#cognome").val();
    if(cognome.length<=2)
    {
        return false;
    }
    return true;
}

function controllaTelefonoRegistra()
{
    const tel= $("#tel").val();
    if(tel.length!=10)
    {
        return false;
    }
    return true;
}

function controllaEmailRegistra()
{
    const email= $("#emailRegistra").val();
    if(email.length<=5)
    {
        return false;
    }
    return true;
}

function controllaPswRegistra()
{
    const psw= $("#pswRegistra").val();
    if(psw.length<4)
    {
        return false;
    }
    return true;
}

$("#registraBtn").click(function ()
{
    //controllo della email

    if(controllaNomeRegistra()==false)
    {
        $("#messaggioErrore").fadeIn();
        $("#nome").css("border", " 1px solid red");
        messaggioErrore+="<br/>Nome non valido";
    }
    else
    {
        $("#nome").css("border", " 1px solid green");
    }

    if(controllaCognomeRegistra()==false)
    {
        $("#messaggioErrore").fadeIn();
        $("#cognome").css("border", " 1px solid red");
        messaggioErrore+="<br/>Cognome non valido";
    }
    else
    {
        $("#cognome").css("border", " 1px solid green");
    }

    if(controllaTelefonoRegistra()==false)
    {
        $("#messaggioErrore").fadeIn();
        $("#tel").css("border", " 1px solid red");
        messaggioErrore+="<br/>Telefono non valido (lunghezza 10 cifre)";
    }
    else
    {
        $("#tel").css("border", " 1px solid green");
    }

    if(controllaEmailRegistra()==false)
    {
        $("#messaggioErrore").fadeIn();
        $("#emailRegistra").css("border", " 1px solid red");
        messaggioErrore+="<br/>Email non valida";
    }
    else
    {
        $("#emailRegistra").css("border", " 1px solid green");
    }

    //controllo della psw

    if(controllaPswRegistra()==false)
    {
        $("#messaggioErrore").fadeIn();
        $("#pswRegistra").css("border", " 1px solid red");
        messaggioErrore+="<br/>Password non valida";
    }
    else
    {
        $("#pswRegistra").css("border", " 1px solid green");
    }

    testoErrore.html(messaggioErrore);

    if(messaggioErrore.length<1)
    {
        $("#messaggioErrore").fadeOut();
        $("#pswRegistra").css("border", " 1px solid green");
    }

    messaggioErrore="";
});
