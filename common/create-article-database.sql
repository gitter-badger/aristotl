-- Bootstraps the article table
create table articles (
       id bigserial primary key,
       title varchar(255) not null,
       lede text       
)
