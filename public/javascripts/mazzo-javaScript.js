
$(function(){
    
    $("[href]").each(function() {
    if (this.href === window.location.href) {
        $(this).addClass("active");
        }
    });
   
    //Accordion
    $("#latest_news").accordion({header:"h3"});
    
    //Image Rotator
    $(".rslides").responsiveSlides();
    
    //Slide show
    //setInterval ("rotateImages()",4000);
    
    //Sidebar
    $(".sidebar").hover(highlight1, highlight2);
    
    //H1 Header
    $(".brand").hover(highlight3, highlight3);
    
    //Main Content
    $(".mainContent").hover(highlight4, highlight5);
    
    
    //Functions
    function highlight1(evt) {
        $(".sidebar").toggleClass ("alt_color1");
    }
         
    function highlight2(evt) {
        $(".sidebar").toggleClass ("alt_color2");
    }
    
    function highlight3(evt) {
        $(".brand").toggleClass ("brand_alt_color1");
    }
    
    function highlight4(evt) {
        $(".mainContent").toggleClass ("mainContent_alt_color1");
    }
		
    function highlight5(evt) {
        $(".mainContent").toggleClass ("mainContent_alt_color2");
    }
    
    function rotateImages(){
        var oCurPhoto = $('#photoShow div.current');
        var oNxtPhoto = oCurPhoto.next();
            if(oNxtPhoto.length===0){
                oNxtPhoto = $('#photoShow div:first');
                oCurPhoto.removeClass('current').addClass('previous');
                oNxtPhoto.css({opacity: 0.0}).addClass('current').animate({opacity:1.0}, 2000,function(){oCurPhoto.removeClass('previous');});
            }
        } 
   


    
    });//End Document ready
 
