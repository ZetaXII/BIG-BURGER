const hamburgerBtn= document.querySelector('.hamburger');
const menuCompatto= document.querySelector('.menuCompatto');
const btnCarrelloCompatto= document.querySelector('.btnCarrelloCompatto');
const btnChiudiMenuCompatto= document.querySelector('.btnChiudiMenuCompatto');

if(hamburgerBtn)
{
    hamburgerBtn.addEventListener('click', () =>
    {
        menuCompatto.classList.toggle("mostra");
        menuCompatto.classList.remove("nascondi");
    });
}

if(btnChiudiMenuCompatto)
{
    btnChiudiMenuCompatto.addEventListener('click', () =>
    {
        menuCompatto.classList.toggle("nascondi");
        menuCompatto.classList.remove("mostra");
    });
}

if(btnCarrelloCompatto)
{
    btnCarrelloCompatto.addEventListener('click', () =>
    {
        menuCompatto.classList.toggle("nascondi");
        menuCompatto.classList.remove("mostra");
        carrello.classList.toggle("sposta");
    });
}

