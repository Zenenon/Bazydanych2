-- Wyzwalacz dla wstawiania danych
DELIMITER //

CREATE TRIGGER LogMaszynyInsert
AFTER INSERT ON Maszyny
FOR EACH ROW
BEGIN
    INSERT INTO DziennikOperacji (TypOperacji, Opis, MaszynaID)
    VALUES ('Dodanie', CONCAT('Dodano nową maszynę: ', NEW.Nazwa), NEW.ID);
END //  

DELIMITER ;


-- Wyzwalacz dla aktualizacji statusu
DELIMITER //

CREATE TRIGGER LogZmianaStatusu
AFTER UPDATE ON Maszyny
FOR EACH ROW
BEGIN
    -- Sprawdzenie, czy zmieniono kolumnę Status
    IF OLD.Status <> NEW.Status THEN
        -- Pobranie opisu starego i nowego statusu
        SET @staryStatusOpis = (SELECT Opis FROM Statusy WHERE ID = OLD.Status);
        SET @nowyStatusOpis = (SELECT Opis FROM Statusy WHERE ID = NEW.Status);

        -- Dodanie wpisu do dziennika operacji
        INSERT INTO DziennikOperacji (TypOperacji, Opis, MaszynaID)
        VALUES (
            'Zmiana statusu',
            CONCAT('Zmieniono status maszyny ID: ', NEW.ID, 
                   ' z "', @staryStatusOpis, '" na "', @nowyStatusOpis, '"'),
            NEW.ID
        );
    END IF;
END //

DELIMITER ;

-- Wyzwalacz dla usuwania danych
DELIMITER //

CREATE TRIGGER LogMaszynyDelete
AFTER DELETE ON Maszyny
FOR EACH ROW
BEGIN
    INSERT INTO DziennikOperacji (TypOperacji, Opis, MaszynaID)
    VALUES ('Usunięcie', CONCAT('Usunięto maszynę: ', OLD.Nazwa), OLD.ID);
END //

DELIMITER ;
