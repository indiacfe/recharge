$(document).ready(function() {
    
    var top=-1*(parseInt($('.overlay-content').height())+20);
	$('#overlay').css('margin-top', top+'px');
	
    $('#overlay-trigger').click(function(){
       var top=-1*(parseInt($('.overlay-content').height())+20);
       if($('#overlay-trigger').find('.active').length){
		$('#overlay').animate({ 'marginTop': top+'px'}, 1000);
			$('#overlay-trigger').find('a').removeClass('active');
       }else{
    	$('#overlay').animate({ 'marginTop': '0px'}, 1000);
     	$('#overlay-trigger').find('a').addClass('active');
     	}
    });
    
    
    $('.carousel').carousel({
      interval: 4000
    })
 	$('body').tooltip({
 	    selector: '[rel=tooltip]'
 	}); 
    $('.video').fitVids();  
 });
 
 
 $(window).resize(function() {
    var top=-1*(parseInt($('.overlay-content').height())+20);
    $('#overlay').css('margin-top', top+'px');
    $('#overlay-trigger').find('a').removeClass('active');
 });
 
 $(document).ready(function() {

 
 $('a[rel=popover]').popover();
 
 
 Cufon.replace('p, span, .pricing-table-column li, .pricing-table-column li a',{ fontFamily: 'Colaborate Light'});
 Cufon.replace('h1,h2,h3,h4,h5,h6,.accordion-toggle',{ fontFamily: 'Oswald Cufon'});
 });
 
   $(document).ready(function() {
   		 $('nav a').mouseenter(function(){
   		
   		  		if($(this).attr('title').length){
   				   if(!$(this).find('span').length){
   				 $(this).append('<span>'+$(this).attr('title')+'</span>');
   				 }
   				$(this).find('span').stop(true,true).animate({width: '70px', opacity: 0.8},400);
   				}
   		  });
   		  
   		  $('nav a').mouseleave(function(){
   		  	if($(this).attr('title').length){
   		  	
   		     	$(this).find('span').animate({
   		     		width: 0, 
   		     		opacity: 0
   		     		},400);
   		     	
   		     	     	  
   		     	
   		  		}
   		});
   
   
   })
  $(document).ready(function() {
    
   $('.hover_top_in').mouseenter(function(){
  		$(this).append('<span style="height:0"><img src="img/magnify.png" /></span>');
  		$(this).find('span').animate(	{height: '100%', opacity: 0.8},400);
 });
 
 $('.hover_top_in').mouseleave(function(){
    $(this).find('span').animate({height: 0, opacity: 0},400);
 	$(this).find('span').remove();
   });
   
   $('.hover_left_in').mouseenter(function(){
    		$(this).append('<span style="width:0"><img src="img/magnify.png" /></span>');
    		$(this).find('span').animate(	{width: '100%', opacity: 0.8},400);
   });
   
   $('.hover_left_in').mouseleave(function(){
      $(this).find('span').animate({width: 0, opacity: 0},400);
   	$(this).find('span').remove();
     });
   
   $('.hover_video_top_in').mouseenter(function(){
    		$(this).append('<span style="height:0"><img src="img/video.png" /></span>');
    		$(this).find('span').animate(	{height: '100%', opacity: 0.8},400);
   });
   
   $('.hover_video_top_in').mouseleave(function(){
      $(this).find('span').animate({height: 0, opacity: 0},400);
   	$(this).find('span').remove();
     });
     
     $('.hover_video_left_in').mouseenter(function(){
      		$(this).append('<span style="width:0"><img src="img/video.png" /></span>');
      		$(this).find('span').animate(	{width: '100%', opacity: 0.8},400);
     });
     
     $('.hover_video_left_in').mouseleave(function(){
        $(this).find('span').animate({width: 0, opacity: 0},400);
     	$(this).find('span').remove();
       });
     
    /* 
     $('nav a').not("li.dropdown-menu").click(function(){
     		event.preventDefault();
     		var target=$(this).attr('href');
			$('html,body').animate({scrollTop: $(target).offset().top},'slow');
			$('nav').find('.active').removeClass('active');
			$(this).parent().addClass('active');
       });
      */ 
     
     
       
 });

$(document).ready(function() { 
 // Cache selectors
 var lastId;
 var topMenu = $("nav #mainmenu"); 
 var topMenuHeight = 0;//topMenu.outerHeight()+15
     // All list items
 var menuItems = topMenu.find("a"),
     // Anchors corresponding to menu items
     scrollItems = menuItems.map(function(){
       var item = $($(this).attr("href"));
       
       if (item.length) { return item; }
     });
  

 // Bind click handler to menu items
 // so we can get a fancy scroll animation
 menuItems.click(function(e){
   var href = $(this).attr("href"),
       offsetTop = href === "#" ? 0 : $(href).offset().top-topMenuHeight+1;
       
  
   $('html, body').stop().animate({ 
       scrollTop: offsetTop
   }, 400);
   event.preventDefault();
 });


        $(window).scroll( function ()
        {
            var fromTop = $(this).scrollTop()+25;
            var cur = scrollItems.map(function(){
              if ($(this).offset().top < fromTop)
                return this;
            });
            cur = cur[cur.length-1];
            var id = cur && cur.length ? cur[0].id : "";
            if (lastId !== id) {
                lastId = id;
                menuItems
                  .parent().removeClass("active");
                  menuItems.filter("[href=#"+id+"]").parent().addClass("active");               
                   }
        });
    });

/*==== Vibe Options Panel ==== */
$(document).ready(function() { 

$('#vibeoptions').css({'margin-right': '-200px'});
$('#vibeoptionsopener').click(function(){

  $('#vibeoptions_text').hide();
  if($('#vibeoptions').hasClass('open')){
  	$('#vibeoptions').animate({'marginRight': '-200px'},400);
  	$('#vibeoptions').removeClass('open');
  }else{
	$('#vibeoptions').animate({'marginRight': 0},400);
	$('#vibeoptions').addClass('open');
	}
});

	$('#coloroptions li').click(function(){
		$('#coloroptions').find('.vibe_active').removeClass('vibe_active');
		$(this).addClass('vibe_active');
		
		var stl='css/style'+$(this).text()+'.css'
		 $('#stylesheet[rel=stylesheet]').attr('href', stl);
		 
	});
	
	
	$('#backgroundoptions li').click(function(){
		$('#backgroundoptions').find('.vibe_active').removeClass('vibe_active');
		
		var bg='url("img/bg/bg'+$(this).text()+'.png")';
		
		 $('#overlay .overlay-content').css({'background': bg});
		 $('.slider_form .conversion_form').css({'background': bg});
		 $('.footerarea').css({'background': bg});
		$(this).addClass('vibe_active');
	});

});


// Contact Form
  $(document).ready(function() {

      function send(datastr,content) {
      	jQuery.ajax({
              type: "POST",
              url: "php/mail.php",
              data: {email:datastr, content: content},
              cache: false,
              success: function (html) {
              
                  setTimeout(function(){
                  jQuery(".sendmail").removeClass("disabled");
                  jQuery(".sendmail").html(html);
                  setTimeout(function(){jQuery(".sendmail").html('Send<i class="icon-white icon-chevron-right"></i>');}, 2000);
                  }, 2000);
              }
          });
      }

          $(".sendmail").click(function () {
              event.preventDefault();
              var valid = "";
              var mail = $('.email').val();
              if (!mail.match(/^([a-z0-9._-]+@[a-z0-9._-]+\.[a-z]{2,4}$)/i)) {
                  valid += "Invalid&nbsp;email";
              }
              if (valid != "") {
                  $('.email').after("<a class='error_icon tooltip' rel='tooltip' title="+ valid + "></a>");
                   $('.email').addClass('error_input');
                   $('.email').tooltip();
              } else {
                  var data =  mail;
                  var content=$('#contact_text').val();
                  if($('.email').hasClass('error_input')){
                  $('.email').removeClass('error_input');
                  $('.footercontact .error_icon').remove();
                  };
                  $(".sendmail").addClass('disabled');
                  $(".sendmail").html('sending email...');
                  setTimeout(function(){send(data,content);}, 2000);
              }
              return false;
          });
          
          jQuery(".email").keypress(function(e) {
              if(e.keyCode == 13) {
                  jQuery(".sendmail").click();
                  return false;
              }

      });
      

      
});