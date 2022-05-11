CREATE TABLE if NOT EXISTS pais (
    id bigserial,
    nome CHARACTER VARYING,
    nome_pt CHARACTER VARYING,
    sigla CHARACTER VARYING,
    bacen BIGINT,
    CONSTRAINT pk_pais PRIMARY KEY (id)
);

CREATE TABLE if NOT EXISTS estado (
    id bigserial,
    uf CHARACTER VARYING,
    nome CHARACTER VARYING,
    ativo BOOLEAN,
    ibge BIGINT,
    pais_id BIGINT,
    ddd CHARACTER VARYING,
    CONSTRAINT pk_estado PRIMARY KEY (id),
    CONSTRAINT fk_pais FOREIGN KEY (pais_id) REFERENCES pais (id)
);

CREATE TABLE if NOT EXISTS cidade (
    id bigserial,
    nome CHARACTER VARYING,
    cep BIGINT,
    ibge BIGINT,
    estado_id BIGINT,
    ativo BOOLEAN,
    lat_lon CHARACTER VARYING,
    latitude DOUBLE PRECISION,
    longitude DOUBLE PRECISION,
    cod_tom BIGINT,
    CONSTRAINT pk_cidade PRIMARY KEY (id),
    CONSTRAINT fk_estado FOREIGN KEY (estado_id) REFERENCES estado (id)
);

CREATE TABLE if NOT EXISTS permissao (
    id bigserial,
    descricao CHARACTER VARYING,
    ativo BOOLEAN,
    visivel BOOLEAN,
    CONSTRAINT pk_permissao PRIMARY KEY (id)
);

CREATE TABLE if NOT EXISTS pessoa_juridica (
    id bigserial,
    razao_social CHARACTER VARYING,
    nome_fantasia CHARACTER VARYING,
    cnpj CHARACTER VARYING,
    cep CHARACTER VARYING,
    logradouro CHARACTER VARYING,
    bairro CHARACTER VARYING,
    numero CHARACTER VARYING,
    complemento CHARACTER VARYING,
    ativo BOOLEAN,
    cidade_id BIGINT,
    foto bytea,
    CONSTRAINT pk_pessoa_juridica PRIMARY KEY (id),
    CONSTRAINT fk_cidade FOREIGN KEY (cidade_id) REFERENCES cidade (id)
);

CREATE TABLE if NOT EXISTS escritorio (
    id bigserial,
    ativo BOOLEAN,
    pessoa_juridica_id BIGINT,
    valor_base NUMERIC(15, 2),
    valor_por_cliente NUMERIC(15, 2),
    base_cliente NUMERIC(15, 2),
    data_adessao DATE,
    CONSTRAINT pk_escritorio PRIMARY KEY (id),
    CONSTRAINT fk_pessoa_juridica FOREIGN KEY (pessoa_juridica_id) REFERENCES pessoa_juridica (id)
);

CREATE TABLE if NOT EXISTS empresa (
    id bigserial,
    ativo BOOLEAN,
    pessoa_juridica_id BIGINT,
    escritorio_id BIGINT,
    uf CHARACTER VARYING,
    CONSTRAINT pk_empresa PRIMARY KEY (id),
    CONSTRAINT fk_pessoa_juridica FOREIGN KEY (pessoa_juridica_id) REFERENCES pessoa_juridica (id),
    CONSTRAINT fk_escritorio FOREIGN KEY (escritorio_id) REFERENCES escritorio (id)
);

CREATE TABLE if NOT EXISTS pessoa (
    id bigserial,
    nome CHARACTER VARYING,
    cpf CHARACTER VARYING,
    rg CHARACTER VARYING,
    telefone1 CHARACTER VARYING,
    telefone2 CHARACTER VARYING,
    logradouro CHARACTER VARYING,
    numero CHARACTER VARYING,
    complemento CHARACTER VARYING,
    bairro CHARACTER VARYING,
    cidade_id BIGINT,
    cep CHARACTER VARYING,
    uf CHARACTER VARYING,
    estado CHARACTER VARYING,
    foto bytea,
    ativo BOOLEAN,
    escritorio_id BIGINT,
    CONSTRAINT pk_pessoa PRIMARY KEY (id),
    CONSTRAINT fk_escritorio FOREIGN KEY (escritorio_id) REFERENCES escritorio (id),
    CONSTRAINT fk_cidade FOREIGN KEY (cidade_id) REFERENCES cidade (id)
);

CREATE TABLE if NOT EXISTS perfil_usuario (
    id bigserial,
    nome CHARACTER VARYING,
    escritorio_id BIGINT,
    CONSTRAINT pk_perfil_usuario PRIMARY KEY (id),
    CONSTRAINT fk_escritorio FOREIGN KEY (escritorio_id) REFERENCES escritorio (id)
);

CREATE TABLE if NOT EXISTS usuario (
    id bigserial,
    login CHARACTER VARYING,
    email CHARACTER VARYING,
    senha CHARACTER VARYING,
    data_bloqueio DATE,
    ativo BOOLEAN,
    pessoa_id BIGINT,
    perfil_usuario_id BIGINT,
    CONSTRAINT pk_usuario PRIMARY KEY (id),
    CONSTRAINT fk_pessoa FOREIGN KEY (pessoa_id) REFERENCES pessoa (id),
    CONSTRAINT fk_perfil_usuario FOREIGN KEY (perfil_usuario_id) REFERENCES perfil_usuario (id)
);

CREATE TABLE permissao_perfil_usuario (
    perfil_usuario_id int8 NOT NULL,
    permissao_id int8 NOT NULL,
    CONSTRAINT fk_permissao FOREIGN key (permissao_id) REFERENCES permissao (id),
    CONSTRAINT fk_perfil_usuario FOREIGN key (perfil_usuario_id) REFERENCES perfil_usuario (id)
);

CREATE TABLE if NOT EXISTS certificado (
    id bigserial,
    ipo CHARACTER VARYING,
    emitido CHARACTER VARYING,
    validade_ini DATE,
    validade_fim DATE,
    serial CHARACTER VARYING,
    pin BIGINT,
    ativo BOOLEAN,
    senha CHARACTER VARYING,
    arquivo bytea,
    empresa_id BIGINT,
    CONSTRAINT pk_certificado PRIMARY KEY (id),
    CONSTRAINT fk_empresa FOREIGN KEY (empresa_id) REFERENCES empresa (id)
);
