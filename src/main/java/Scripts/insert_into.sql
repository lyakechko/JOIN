insert into test.people_address (address_id,people_id)
select ad.id, peop.id from test.address ad, test.people peop
where ad.id=22 and  peop.id=53;