--Ejecutar primero
DROP DATABASE Festivos WITH (FORCE);
--Ejecutar segundo
CREATE DATABASE Festivos; 

--Para las siguientes instrucciones, se debe cambiar la conexión

--Crear la tabla TIPO
CREATE TABLE Tipo(
	Id SERIAL PRIMARY KEY,
	Tipo VARCHAR(100) NOT NULL
	);

--Crear la tabla FESTIVO
CREATE TABLE Festivo(
	Id SERIAL PRIMARY KEY,
	Nombre VARCHAR(100) NOT NULL,
	Dia INT NOT NULL,
	Mes INT NOT NULL,
	DiasPascua INT NOT NULL,
	IdTipo INT NOT NULL,
	CONSTRAINT fkFestivo_Tipo FOREIGN KEY (IdTipo) REFERENCES Tipo(Id)
	);

--Calculo domingo de pascua
CREATE OR REPLACE FUNCTION domingo_pascua(ano integer)
RETURNS DATE AS $$
DECLARE
    a integer;
    b integer;
    c integer;
    d integer;
	dias integer;
BEGIN
    a := ano % 19;
    b := ano % 4;
    c := ano % 7;
    d := (19 * a + 24) % 30;
	dias := d+ (2 * b + 4 * c + 6 * d + 5) % 7;
	
	RETURN TO_DATE(ano || '-03-15', 'YYYY-MM-DD')+dias+7;
END;
$$ LANGUAGE plpgsql;



--Función para determinar si es Lunes=0, Martes=1, Miercoles=2,... 
CREATE OR REPLACE FUNCTION dia_semana(dia integer, mes integer, ano integer)
RETURNS integer AS $$
DECLARE
    h integer;
    m integer;
    K integer;
    J integer;
BEGIN
    IF mes < 3 THEN
        m := mes + 12;
        K := (ano - 1) % 100;
        J := (ano - 1) / 100;
    ELSE
        m := mes;
        K := ano % 100;
        J := ano / 100;
    END IF;

    h := (dia + ((13 * (m + 1)) / 5)::integer + K + (K / 4)::integer + (J / 4)::integer + 5 * J) % 7;

    IF h < 2 THEN
        h := h + 5;
    ELSE
        h := h - 2;
    END IF;

    RETURN h;
END;
$$ LANGUAGE plpgsql;
