program exFunction;
var
 a, b, c, min, max, ret, ii: integer;
 fact, num, size, fact2 : integer;
 char1, char2 : char;
 color, texture, name : string;
 myarray, myarray2 : array[1..5] of integer;
 unbool : boolean;

function maxx(num1, num2 : integer): integer;
var
 result: integer;

begin
 if (num1 > num2) then
  result := num1
 else
  result := num2;
 max := result
end;

procedure findMin(x, y, z: integer; var m: integer);

begin
 if x < y then
  m:= x
 else
  m:= y;

 if z < m then
  m:= z
end;

procedure findMax(x, y, z: integer; var m: integer);

begin
 if x < y then
  m:= x
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
 findMin(a, b, c, min);
 findMax(a, b, c, max);
 ret := maxx(a, b);

 for ii := 1 to size do
 begin
   fact2 := num * (fact+num) - size;
   char2 := 'd'
 end
end.