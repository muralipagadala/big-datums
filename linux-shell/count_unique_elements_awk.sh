cat sample_data_1.txt \
	| awk 'BEGIN{FS="\t"} NR>1{names[$2]=1} END{print length(names)}
