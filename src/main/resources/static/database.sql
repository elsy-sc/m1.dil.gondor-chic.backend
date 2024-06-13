//////////////: database : gondor_chic_db
/////////////:  schema : magic_vente_stock

CREATE SEQUENCE magic_vente_stock.produit_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

CREATE OR REPLACE FUNCTION magic_vente_stock.generate_produit_id()
RETURNS TRIGGER AS $$
DECLARE
new_id VARCHAR;
BEGIN
new_id := 'product_' || nextval('magic_vente_stock.produit_id_seq');
NEW.id := new_id;
RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE TABLE magic_vente_stock.t_produit (
     id VARCHAR PRIMARY KEY,
     photo VARCHAR,
     reference VARCHAR,
     libelle VARCHAR,
     estDuJour BOOLEAN,
     prix FLOAT,
     quantiteEnStock INT
);


CREATE TRIGGER set_produit_id
    BEFORE INSERT ON magic_vente_stock.t_produit
    FOR EACH ROW
    EXECUTE FUNCTION magic_vente_stock.generate_produit_id();


INSERT INTO magic_vente_stock.t_produit (photo, reference, libelle, estDuJour, prix, quantiteEnStock) VALUES ('wawawabase64', 'REF001', 'Product A', TRUE, 10.99, 100);


CREATE TABLE magic_vente_stock.role (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(50) NOT NULL
);


CREATE TABLE magic_vente_stock.client (
   id SERIAL PRIMARY KEY,
   nom VARCHAR NOT NULL,
   prenom VARCHAR NOT NULL,
   username VARCHAR NOT NULL,
   email VARCHAR(100) NOT NULL UNIQUE,
   password VARCHAR(255) NOT NULL,
   id_role INT,
   FOREIGN KEY (id_role) REFERENCES magic_vente_stock.role(id)
);


INSERT INTO magic_vente_stock.role (nom) VALUES ('client'), ('employe');