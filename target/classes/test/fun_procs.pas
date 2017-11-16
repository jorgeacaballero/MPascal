program exFunction;
var
 a, b, c, min, max, ret, ii: integer;
 fact, num, size, fact2 : integer;
 char1, char2 : char;
 color, texture, name : string;
 myarray, myarray2 : array[1..5] of integer;
 unbool : boolean;

procedure findMax(x, y, z: integer; var m: integer);

begin
 if x < y then
  m:= x + 1;
 else
  m:= y;

 if z < m then
  m:= z
end;


begin
 a := 100;
 b := 200;
 c := 300;
 num := 2;
 char2 := 'd';
 color := 'rojo';
 findMax(a, b, c, max);
 


 if a > b then
 a:= a + 1;
 

 for ii := 1 to size do
 begin
   fact2 := num * (fact+num) - size;
   char2 := 'd'
 end
end.