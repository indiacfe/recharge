/*
Name: VibeLander Slider
Author: VibeThemes (vibethemes.com | vibethemes@gmail.com)
Copyright VibeThemes
This File is copyrighted and is part of template VibeLander.
This slider follows same license terms as the main template.
*/

$(document).ready(function(){var e=$('.active').find('.slide_caption');var f=e.find('.slide_title');var g=e.find('.slide_content');var h=e.find('.slide_action');va(f);va(g);va(h);$(".carousel").bind("slid",function(){var a=$('.active').find('.slide_caption');var b=a.find('.slide_title');var c=a.find('.slide_content');var d=a.find('.slide_action');va(b);va(c);va(d)});$(".carousel").bind("slide",function(){var a=$('.active').find('.slide_caption');var b=a.find('.slide_title');var c=a.find('.slide_content');var d=a.find('.slide_action');b.css({'opacity':'0'});c.css({'opacity':'0'});d.css({'opacity':'0'})})});function via(a){var b=a.attr('data-effect');switch(b){case'left-in':{a.css({'left':'-=50','opacity':'0'});break}case'top-in':{a.css({'top':'-=50','opacity':'0'});break}case'bottom-in':{a.css({'top':'+=50','opacity':'0'});break}case'right-in':{a.css({'left':'+=50','opacity':'0'});break}default:{a.css({'opacity':'0'})}}}function va(a){var b=a.attr('data-effect');via(a);switch(b){case'left-in':{a.animate({left:'+=50',opacity:1},{queue:true,duration:600});break}case'top-in':{a.animate({top:'+=50',opacity:1},{queue:true,duration:500});break}case'bottom-in':{a.animate({top:'-=50',opacity:1},{queue:true,duration:500});break}case'right-in':{a.animate({left:'-=50',opacity:1},{queue:true,duration:500});break}default:{a.animate({opacity:1},500)}}}