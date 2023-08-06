<?php
use PHPMailer\PHPMailer\PHPMailer;
use PHPMailer\PHPMailer\SMTP;
use PHPMailer\PHPMailer\Exception;

 require 'phpmailer/src/Exception.php'; 
 require 'phpmailer/src/PHPMailer.php'; 
 require 'phpmailer/src/SMTP.php'; 

$otp = generateOTP();
$mail = new PHPMailer(true);

try {
    //Server settings
    $mail->SMTPDebug = SMTP::DEBUG_SERVER;                      
    $mail->isSMTP();                                            
    $mail->Host       = 'smtp.gmail.com';                     
    $mail->SMTPAuth   = true;                                   
    $mail->Username   = 'ltbang29092003@gmail.com';      
    $mail->Password   = 'szzhkiuhelimoioe';      
    $mail->SMTPSecure = PHPMailer::ENCRYPTION_STARTTLS;           
    $mail->Port       = 587;                                    

    //Recipients
    $mail->setFrom('ltbang29092003@gmail.com', 'Thanh Bang');
    $mail->addAddress('thanhbang2909@gmail.com', 'Thanh Bang');     
  
    $mail->addReplyTo('ltbang29092003@gmail.com', 'Thanh Bang');
    

    
    $mail->isHTML(true);                                  
    $mail->Subject = 'Here is the subject';
    $mail->Body    = 'Mã OTP là :' .$otp;
    $mail->AltBody = 'This is the body in plain text for non-HTML mail clients';

    $mail->send();
    echo 'Message has been sent';
} catch (Exception $e) {
    echo "Message could not be sent. Mailer Error: {$mail->ErrorInfo}";
}


//---------------------------------------//
function generateOTP() {
    $otpLength = 6; 
    $otp = '';
    $characters = '0123456789';

    for ($i = 0; $i < $otpLength; $i++) {
        $otp .= $characters[rand(0, strlen($characters) - 1)];
    }

    return $otp;
}