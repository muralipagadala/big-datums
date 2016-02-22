#get 25th line of sample_data_1.txt

#with head/tail
cat sample_data_1.txt | head -25 | tail -1

#with sed p/print
cat sample_data_1.txt | sed -n '25p'

#with sed d/delete
cat sample_data_1.txt | sed '25!d'

#3 variations with awk "NR"
cat sample_data_1.txt | awk 'NR==25'
cat sample_data_1.txt | awk 'NR==25{print}'
cat sample_data_1.txt | awk '{if(NR==25) print}'
