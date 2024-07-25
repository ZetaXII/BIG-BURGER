$(document).ready(()=>
{
    const idOrdine=$("#ordAjax").text().substring($("#ordAjax").text().indexOf('#')+1);
    $.ajax
    ({
        url: "ordineJSON?idOrdine="+idOrdine,
        type: "POST",
        dataType: "json",
        async: true,
        success: function(data)
        {
            if(data)
            {
                var progresso=0;

                if(data.stato==="Ordinato")
                {
                    progresso=25;
                }
                else if(data.stato==="In preparazione")
                {
                    progresso=50;
                }
                else if(data.stato==="In spedizione")
                {
                    progresso=75;
                }
                else if(data.stato==="Consegnato")
                {
                    progresso=100;
                }

                $('#barra').width(progresso + "%").attr('aria-valuenow', progresso);
            }
        }
    })
})