create table swap_trades(
                            id uuid primary key,
                            trade_date date not null,
                            counterparty varchar(128) not null,
                            notional numeric(18,2) not null,
                            fixed_rate numeric(9,6),
                            floating_index varchar(64),
                            status varchar(24) not null
);
