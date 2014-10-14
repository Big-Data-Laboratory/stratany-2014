/*
 * Copyright 2014 Silicon Valley Data Science.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
from kafka.client import KafkaClient
from kafka.producer import SimpleProducer
kafka =  KafkaClient("localhost:9092")
producer = SimpleProducer(kafka, batch_send=True,batch_send_every_n=2000,batch_send_every_t=1)
 
import nltk
import string
from collections import Counter
def get_tokens():
  with open('test.txt', 'r') as shakes:
   text = shakes.read()
   lowers = text.lower()
   #remove the punctuation using the character deletion step of translate
   no_punctuation = lowers.translate(None, string.punctuation)
   tokens = nltk.word_tokenize(no_punctuation)
   return tokens


from nltk.corpus import stopwords
tokens = get_tokens()
filtered = [w for w in tokens if not w in stopwords.words('english')]
from nltk.stem.porter import *
def stem_tokens(tokens, stemmer):
   stemmed = []
   for item in tokens:
       stemmed.append(stemmer.stem(item))
   return stemmed

 
stemmer = PorterStemmer()
stemmed = stem_tokens(filtered, stemmer)
for word in stemmed:
   producer.send_messages("demo",str(word))


