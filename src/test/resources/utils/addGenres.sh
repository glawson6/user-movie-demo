#!/usr/bin/env bash
http --verbose POST localhost:8080/genres name=action   genreid=06d7bee4-fe6e-47d9-b699-1695eae80ce7
http --verbose POST localhost:8080/genres name=comedy   genreid=de962df6-dcf5-4d55-b5ea-673502d6b359
http --verbose POST localhost:8080/genres name=drama    genreid=e833dceb-3ba3-42d7-83ff-d6fa33103da2
http --verbose POST localhost:8080/genres name=crime    genreid=1032ccf4-9079-4273-b071-369310485d6a
http --verbose POST localhost:8080/genres name=romance  genreid=6aec089a-792b-42cd-8b16-5ef17167e39d
http --verbose POST localhost:8080/genres name=horror   genreid=83b68551-741b-48f1-8a9a-3c80e58ceb1f
