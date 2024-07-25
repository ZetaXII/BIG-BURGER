const iconaApri= document.querySelector('.btnCarrello');
const iconaChiudi= document.querySelector('.btnChiudiCarrello');
const carrello= document.querySelector('.carrello');

if(iconaApri)
{
    iconaApri.addEventListener('click', () =>
    {
        carrello.classList.toggle("sposta");
    });
}

if(iconaChiudi)
{
    iconaChiudi.addEventListener('click', () =>
    {
        carrello.classList.toggle("sposta");
    });
}

/*
window.addEventListener('beforeunload', function (e) {
    e.preventDefault();
    e.returnValue = '';
});*/