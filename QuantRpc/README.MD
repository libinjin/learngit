### 破解DNA需求更新记录

```
2017-04-14

alter table `strategy_trade`.`user_loop` 
   add column `type` int(11) DEFAULT '0' NULL COMMENT '0 回测   1破解DNA' after `share_num`;

DROP INDEX index_user_id ON user_loop;

CREATE UNIQUE INDEX inx_user_id_type ON user_loop (user_id,TYPE);


alter table `strategy_trade`.`user_loop_record` 
   add column `type` int(11) DEFAULT '0' NULL COMMENT '0 回测 1 破解DNA' after `user_id`;




zookeeper:
dna.loopnum=1
dna.sharenum=1
dna.strshare=当日免费机会已用尽，您还可以通过分享获得额外1次机会。购买优顾人工智能即可在有效期内免费使用此功能~
dna.strshareend=当日免费机会已用尽。购买优顾人工智能即可在有效期内免费使用此功能~
```