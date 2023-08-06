<?php
use PHPMailer\PHPMailer\PHPMailer;
use PHPMailer\PHPMailer\SMTP;
use PHPMailer\PHPMailer\Exception;

 require 'phpmailer/src/Exception.php'; 
 require 'phpmailer/src/PHPMailer.php'; 
 require 'phpmailer/src/SMTP.php'; 

$to = $_POST['email'];
$message = $_POST['message'];
$subject = $_POST['subject'];
$mail = new PHPMailer(true);

try {

    $mail->SMTPDebug = SMTP::DEBUG_SERVER;                      
    $mail->isSMTP();                                            
    $mail->Host       = 'smtp.gmail.com';                     
    $mail->SMTPAuth   = true;                                   
    $mail->Username   = 'ltbang29092003@gmail.com';      
    $mail->Password   = 'szzhkiuhelimoioe';      
    $mail->SMTPSecure = PHPMailer::ENCRYPTION_STARTTLS;           
    $mail->Port       = 587;                                    


    $mail->setFrom('ltbang29092003@gmail.com', 'Food app');
    $mail->addAddress($to, 'Food app');     
  
    $mail->addReplyTo('ltbang29092003@gmail.com', 'Food app');
    
    
    $mail->isHTML(true);                                  
    $mail->Subject = $subject;
    $mail->Body    = $message;
    $mail->AltBody = 'This is the body in plain text for non-HTML mail clients';
    $mail->CharSet = 'UTF-8';
    $mail->send();
    echo 'Message has been sent';
} catch (Exception $e) {
    echo "Message could not be sent. Mailer Error: {$mail->ErrorInfo}";
}
