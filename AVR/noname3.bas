$regfile = "M16def.dat"
$crystal = 8000000
$baud = 9600

Dim Na As Byte , X As Integer , A As Integer
A = 0
X = 1500

Config Portd.6 = 1

Enable Interrupts

For A = 1 To 20
Portd.6 = 1
Waitus 1500
Portd.6 = 0
Waitus 20000
Next

Do
If Ischarwaiting() = 1 Then
Na = Waitkey()
   ''''''''''''''''''''''''''''''''''''''''''
   If Na = "1" And X < 2000 Then
      Do
         X = X + 20
         Portd.6 = 1
         Waitus X
         Portd.6 = 0
         Waitus 20000
         If Ischarwaiting() = 1 Or X > 2500 Then Exit Do
      Loop
   End If
   ''''''''''''''''''''''''''''''''''''''''''
   If Na = "2" And X > 1000 Then
      Do
         X = X - 20
         Portd.6 = 1
         Waitus X
         Portd.6 = 0
         Waitus 20000
         If Ischarwaiting() = 1 Or X < 500 Then Exit Do
      Loop
   End If
   ''''''''''''''''''''''''''''''''''''''''''
End If

Loop
End